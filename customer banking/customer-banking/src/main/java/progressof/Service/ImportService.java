package progressof.Service;

import progressof.DTO.*;
import progressof.Entity.Identifier;
import progressof.Entity.ImportBatch;
import progressof.Entity.Request;
import progressof.Enum.OwnershipResult;
import progressof.Repository.BatchRepository;
import progressof.Repository.RequestRepository;
import progressof.model.IdentifierResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.time.Instant;
import java.util.*;

@Service
public class ImportService {

    private static final Logger log =
            LoggerFactory.getLogger(
                    ImportService.class);

    private final CsvParserService parserService;

    private final RequestRepository requestRepository;

    private final BatchRepository batchRepository;

    private final IdentifierClassifier classifier =
            new IdentifierClassifier();

    private final OwnershipService ownershipService =
            new OwnershipService();

    public ImportService(
            CsvParserService parserService,
            RequestRepository requestRepository,
            BatchRepository batchRepository) {

        this.parserService = parserService;
        this.requestRepository = requestRepository;
        this.batchRepository = batchRepository;
    }

    public ImportResponseDTO importCsv(
            MultipartFile file) {

        log.info("Starting CSV import");

        ImportResponseDTO response =
                new ImportResponseDTO();

        UUID batchId = UUID.randomUUID();

        response.setBatchId(batchId);

        Set<String> seenIds = new HashSet<>();

        try {

            List<CsvRowDTO> rows =
                    parserService.parse(
                            new InputStreamReader(
                                    file.getInputStream()));

            response.setTotalRows(rows.size());

            for (CsvRowDTO row : rows) {

                String requestId =
                        row.getRequestId();

                log.info(
                        "Processing request {}",
                        requestId);

                // duplicate inside uploaded file
                if (seenIds.contains(requestId)) {

                    response.setDuplicateRows(
                            response.getDuplicateRows() + 1);

                    response.getRows().add(
                            new RowResultDTO(
                                    requestId,
                                    "DUPLICATE",
                                    null
                            )
                    );

                    continue;
                }

                seenIds.add(requestId);

                // duplicate in database
                if (requestRepository.existsById(requestId)) {

                    log.warn(
                            "Duplicate request id {}",
                            requestId);

                    response.setDuplicateRows(
                            response.getDuplicateRows() + 1);

                    response.getRows().add(
                            new RowResultDTO(
                                    requestId,
                                    "DUPLICATE",
                                    null
                            )
                    );

                    continue;
                }

                Request entity = new Request();

                entity.setRequestId(requestId);

                entity.setCustomerReference(
                        row.getCustomerReference());

                entity.setSubmittedAt(
                        Instant.parse(
                                row.getSubmittedAt()));

                List<String> identifiers =
                        List.of(
                                row.getIdentifier1(),
                                row.getIdentifier2(),
                                row.getIdentifier3()
                        );

                List<IdentifierResult> results =
                        new ArrayList<>();

                for (String identifier : identifiers) {

                    IdentifierResult result =
                            classifier.classify(identifier);

                    results.add(result);

                    Identifier identifierEntity =
                            new Identifier();

                    identifierEntity.setRawValue(
                            result.getValue());

                    identifierEntity.setType(
                            result.getType());

                    identifierEntity.setValid(
                            result.isValid());

                    identifierEntity.setErrorMessage(
                            result.getError());

                    identifierEntity.setExtractedCif(
                            result.getExtractedCif());

                    identifierEntity.setRequest(entity);

                    entity.getIdentifiers()
                            .add(identifierEntity);
                }

                OwnershipResult ownership =
                        ownershipService
                                .determine(results);

                entity.setOwnershipResult(
                        ownership);

                requestRepository.save(entity);

                response.setImportedRows(
                        response.getImportedRows() + 1);

                if (ownership ==
                        OwnershipResult.NO_VALID_IDENTIFIERS) {

                    response.setInvalidRows(
                            response.getInvalidRows() + 1);
                }

                response.getRows().add(
                        new RowResultDTO(
                                requestId,
                                "IMPORTED",
                                ownership.name()
                        )
                );
            }

            // save import batch summary
            ImportBatch batch =
                    new ImportBatch();

            batch.setBatchId(batchId);

            batch.setTotalRows(
                    response.getTotalRows());

            batch.setImportedRows(
                    response.getImportedRows());

            batch.setDuplicateRows(
                    response.getDuplicateRows());

            batch.setInvalidRows(
                    response.getInvalidRows());

            batch.setCreatedAt(
                    Instant.now());

            batchRepository.save(batch);

            log.info(
                    "Import batch {} saved successfully",
                    batchId);

        } catch (Exception e) {

            log.error(
                    "Import failed",
                    e);

            throw new RuntimeException(
                    "Import failed",
                    e);
        }

        return response;
    }
}
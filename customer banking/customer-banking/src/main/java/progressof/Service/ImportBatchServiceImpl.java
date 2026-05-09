package progressof.Service;


import progressof.DTO.BatchSummaryDTO;
import progressof.Entity.ImportBatch;
import progressof.Repository.BatchRepository;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ImportBatchServiceImpl
        implements ImportBatchService {

    private final BatchRepository repository;

    public ImportBatchServiceImpl(
            BatchRepository repository) {

        this.repository = repository;
    }

    @Override
    public BatchSummaryDTO getBatch(
            UUID batchId) {

        ImportBatch batch =
                repository.findById(batchId)
                        .orElseThrow();

        BatchSummaryDTO dto =
                new BatchSummaryDTO();

        dto.setBatchId(batch.getBatchId());
        dto.setTotalRows(batch.getTotalRows());
        dto.setImportedRows(batch.getImportedRows());
        dto.setDuplicateRows(batch.getDuplicateRows());
        dto.setInvalidRows(batch.getInvalidRows());

        return dto;
    }
}
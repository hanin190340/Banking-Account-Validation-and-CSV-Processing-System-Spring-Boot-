package progressof.Service;
import progressof.Entity.Identifier;
import progressof.DTO.IdentifierResponseDTO;
import progressof.DTO.RequestResponseDTO;
import progressof.Entity.Request;
import progressof.Exception.RequestNotFoundException;
import progressof.Repository.RequestRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestQueryService {

    private final RequestRepository repository;

    public RequestQueryService(RequestRepository repository) {
        this.repository = repository;
    }

    public RequestResponseDTO getByRequestId(
            String requestId) {

        Request entity =
                repository.findById(requestId)
                        .orElseThrow(() ->
                                new RequestNotFoundException(
                                        "Request not found: "
                                                + requestId));

        return map(entity);
    }

    public List<RequestResponseDTO>
    getByCustomerReference(String reference) {

        return repository
                .findByCustomerReference(reference)
                .stream()
                .map(this::map)
                .toList();
    }

    private RequestResponseDTO map(Request entity) {

        RequestResponseDTO dto =
                new RequestResponseDTO();

        dto.setRequestId(entity.getRequestId());

        dto.setCustomerReference(
                entity.getCustomerReference());

        dto.setSubmittedAt(
                entity.getSubmittedAt());

        entity.getOwnershipResult().name();
        dto.setOwnershipResult(
                entity.getOwnershipResult().name());
        for (Identifier identifier :
                entity.getIdentifiers()) {

            IdentifierResponseDTO idDto =
                    new IdentifierResponseDTO();

            idDto.setValue(
                    identifier.getRawValue());

            idDto.setType(
                    identifier.getType().name());

            idDto.setValid(
                    identifier.isValid());

            idDto.setError(
                    identifier.getErrorMessage());

            idDto.setExtractedCif(
                    identifier.getExtractedCif());

            dto.getIdentifiers().add(idDto);
        }

        return dto;
    }
}






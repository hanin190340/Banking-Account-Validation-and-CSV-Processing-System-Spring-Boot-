package progressof.Service;

import progressof.DTO.ImportResponseDTO;
import progressof.DTO.RequestDTO;
import progressof.Entity.Request;
import progressof.Enum.OwnershipResult;
import progressof.Exception.RequestNotFoundException;
import progressof.Repository.RequestRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AccountCheckServiceImpl
        implements AccountCheckService {

    private final ImportService importService;

    private final RequestRepository requestRepository;

    public AccountCheckServiceImpl(
            ImportService importService,
            RequestRepository requestRepository) {

        this.importService = importService;
        this.requestRepository = requestRepository;
    }

    @Override
    public ImportResponseDTO importCsv(
            MultipartFile file) {

        return importService.importCsv(file);
    }

    @Override
    public RequestDTO getByRequestId(
            String requestId) {

        Request request =
                requestRepository.findById(requestId)
                        .orElseThrow(() ->
                                new RequestNotFoundException(
                                        "Request not found"));

        return mapToDto(request);
    }

    @Override
    public List<RequestDTO> getByCustomerReference(
            String reference) {

        return requestRepository
                .findByCustomerReference(reference)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public List<RequestDTO> getFailedRequests() {

        return requestRepository
                .findByOwnershipResult(
                        OwnershipResult.NO_VALID_IDENTIFIERS)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    private RequestDTO mapToDto(
            Request request) {

        RequestDTO dto =
                new RequestDTO();

        dto.setRequestId(
                request.getRequestId());

        dto.setCustomerReference(
                request.getCustomerReference());

        dto.setOwnershipResult(
                request.getOwnershipResult());

        dto.setSubmittedAt(
                request.getSubmittedAt());

        return dto;
    }
}
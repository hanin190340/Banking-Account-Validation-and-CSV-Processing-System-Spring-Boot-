package progressof.Service;

import org.springframework.web.multipart.MultipartFile;
import progressof.DTO.ImportResponseDTO;
import progressof.DTO.RequestDTO;

import java.util.List;

public interface AccountCheckService {

    ImportResponseDTO importCsv(MultipartFile file);

    RequestDTO getByRequestId(String requestId);

    List<RequestDTO> getByCustomerReference(String reference);
    List<RequestDTO> getFailedRequests();
}

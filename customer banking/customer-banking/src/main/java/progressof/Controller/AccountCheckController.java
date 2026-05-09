package progressof.Controller;

import progressof.DTO.RequestDTO;
import progressof.DTO.ImportResponseDTO;
import progressof.Service.AccountCheckService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@RestController
@RequestMapping("/api/account-checks")
public class AccountCheckController {

    private final AccountCheckService service;

    public AccountCheckController(
            AccountCheckService service) {

        this.service = service;
    }

    @PostMapping(value = "/import", consumes = "multipart/form-data")
    public ImportResponseDTO importFile(
            @RequestParam("file") MultipartFile file) {

        return service.importCsv(file);
    }
    @GetMapping("/failures")
    public List<RequestDTO> getFailures() {
        return service.getFailedRequests();
    }

    @GetMapping("/{requestId}")
    public RequestDTO getById(
            @PathVariable String requestId) {

        return service.getByRequestId(requestId);
    }

    @GetMapping
    public List<RequestDTO> getByCustomer(
            @RequestParam String customerReference) {

        return service.getByCustomerReference(
                customerReference);
    }
}
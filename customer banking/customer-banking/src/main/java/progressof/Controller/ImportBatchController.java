package progressof.Controller;

import org.springframework.web.bind.annotation.*;
import progressof.DTO.BatchSummaryDTO;
import progressof.Service.ImportBatchService;

import java.util.UUID;

@RestController
@RequestMapping("/api/import-batches")
public class ImportBatchController {

    private final ImportBatchService service;

    public ImportBatchController(ImportBatchService service) {
        this.service = service;
    }

    @GetMapping("/{batchId}")
    public BatchSummaryDTO getBatch(@PathVariable UUID batchId) {
        return service.getBatch(batchId);
    }
}
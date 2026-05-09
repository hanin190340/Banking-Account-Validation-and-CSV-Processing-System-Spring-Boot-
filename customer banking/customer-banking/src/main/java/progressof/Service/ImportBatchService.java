package progressof.Service;

import progressof.DTO.BatchSummaryDTO;

import java.util.UUID;

public interface ImportBatchService {

     BatchSummaryDTO getBatch(UUID batchId);
}

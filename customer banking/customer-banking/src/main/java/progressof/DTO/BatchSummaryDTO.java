package progressof.DTO;



import lombok.Data;

import java.util.UUID;
@Data
public class BatchSummaryDTO {

    private UUID batchId;

    private int totalRows;

    private int importedRows;

    private int duplicateRows;

    private int invalidRows;


}

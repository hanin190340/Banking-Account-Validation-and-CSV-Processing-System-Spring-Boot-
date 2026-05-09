package progressof.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class ImportResponseDTO {

    private int totalRows;

    private int importedRows;

    private int duplicateRows;

    private int invalidRows;
    private UUID batchId;

    private List<RowResultDTO> rows =
            new ArrayList<>();
}
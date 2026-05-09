package progressof.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import progressof.Enum.IdentifierType;

@Getter
@Setter
@Data
public class IdentifierDTO {
    private String value;
    private IdentifierType type;
    private boolean valid;
    private String errorMessage;
    private String extractedCif;
}

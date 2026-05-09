package progressof.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import progressof.Enum.OwnershipResult;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Data
public class RequestDTO {
    private String requestId;
    private String customerReference;
    private Instant submittedAt;
    private OwnershipResult ownershipResult;
    private List<IdentifierDTO> identifiers;

}

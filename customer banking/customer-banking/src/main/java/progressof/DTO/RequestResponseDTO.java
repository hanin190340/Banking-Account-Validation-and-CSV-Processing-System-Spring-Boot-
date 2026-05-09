package progressof.DTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class RequestResponseDTO {

    private String requestId;

    private String customerReference;

    private Instant submittedAt;

    private String ownershipResult;

    private List<IdentifierResponseDTO> identifiers =
            new ArrayList<>();

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public Instant getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Instant submittedAt) {
        this.submittedAt = submittedAt;
    }

    public String getOwnershipResult() {
        return ownershipResult;
    }

    public void setOwnershipResult(String ownershipResult) {
        this.ownershipResult = ownershipResult;
    }

    public List<IdentifierResponseDTO> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(
            List<IdentifierResponseDTO> identifiers) {
        this.identifiers = identifiers;
    }
}

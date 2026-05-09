package progressof.DTO;



public class RowResultDTO {

    private String requestId;

    private String status;

    private String ownershipResult;

    public RowResultDTO() {
    }

    public RowResultDTO(String requestId,
                        String status,
                        String ownershipResult) {

        this.requestId = requestId;
        this.status = status;
        this.ownershipResult = ownershipResult;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getStatus() {
        return status;
    }

    public String getOwnershipResult() {
        return ownershipResult;
    }
}
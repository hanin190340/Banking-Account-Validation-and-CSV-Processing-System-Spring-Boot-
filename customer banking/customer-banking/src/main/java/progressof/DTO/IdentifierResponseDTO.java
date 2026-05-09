package progressof.DTO;


public class IdentifierResponseDTO {

    private String value;

    private String type;

    private boolean valid;

    private String error;

    private String extractedCif;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getExtractedCif() {
        return extractedCif;
    }

    public void setExtractedCif(String extractedCif) {
        this.extractedCif = extractedCif;
    }
}

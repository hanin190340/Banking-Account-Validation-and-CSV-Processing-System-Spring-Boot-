package progressof.model;

import lombok.Data;
import progressof.Enum.IdentifierType;
@Data
public class IdentifierResult {

        private String value;

        private IdentifierType type;

        private boolean valid;

        private String error;

        private String extractedCif;

        public IdentifierResult() {
        }

        public IdentifierResult(String value,
                                IdentifierType type,
                                boolean valid,
                                String error,
                                String extractedCif) {
            this.value = value;
            this.type = type;
            this.valid = valid;
            this.error = error;
            this.extractedCif = extractedCif;
        }

        public String getValue() {
            return value;
        }

        public IdentifierType getType() {
            return type;
        }

        public boolean isValid() {
            return valid;
        }

        public String getError() {
            return error;
        }

        public String getExtractedCif() {
            return extractedCif;
        }
    }


package progressof.Service;
import progressof.model.IdentifierResult;
import progressof.Validator.*;
import progressof.util.IdentifierNormalizer;

public class IdentifierClassifier {

    private final IbanValidator ibanValidator =
            new IbanValidator();

    private final AccountNumberValidator accountValidator =
            new AccountNumberValidator();

    private final CifValidator cifValidator =
            new CifValidator();

    public IdentifierResult classify(String value) {

        if (value == null || value.isBlank()) {
            return unknown(value, "Identifier is blank");
        }

        value = value.trim();
        value = IdentifierNormalizer.normalize(value);
        if (value.startsWith("OM")) {
            return ibanValidator.validate(value);
        }

        if (value.matches("\\d{14}")) {
            return accountValidator.validate(value);
        }

        if (value.matches("\\d{7}")) {
            return cifValidator.validate(value);
        }

        return unknown(value, "Unknown identifier format");
    }

    private IdentifierResult unknown(String value, String error) {

        return new IdentifierResult(
                value,
                progressof.Enum.IdentifierType.UNKNOWN,
                false,
                error,
                null
        );
    }
}
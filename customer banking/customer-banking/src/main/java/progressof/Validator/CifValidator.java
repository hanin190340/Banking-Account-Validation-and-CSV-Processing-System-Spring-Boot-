package progressof.Validator;
import progressof.Enum.IdentifierType;
import progressof.model.IdentifierResult;

public class CifValidator implements IdentifierValidator {

    @Override
    public IdentifierResult validate(String value) {

        if (value == null || value.isBlank()) {
            return invalid(value, "Blank CIF");
        }

        value = value.trim();

        if (!value.matches("\\d{7}")) {
            return invalid(value, "CIF must contain exactly 7 digits");
        }

        if (value.equals("0000000")) {
            return invalid(value, "CIF cannot be all zeros");
        }

        return new IdentifierResult(
                value,
                IdentifierType.CIF_NUMBER,
                true,
                null,
                value
        );
    }

    private progressof.model.IdentifierResult invalid(String value, String error) {
        return new IdentifierResult(
                value,
                IdentifierType.CIF_NUMBER,
                false,
                error,
                null
        );
    }
}
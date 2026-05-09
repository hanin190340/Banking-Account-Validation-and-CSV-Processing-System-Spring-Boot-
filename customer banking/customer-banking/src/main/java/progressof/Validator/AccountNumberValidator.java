package progressof.Validator;

import progressof.Enum.IdentifierType;
import progressof.model.IdentifierResult;

public class AccountNumberValidator implements IdentifierValidator {

    @Override
    public IdentifierResult validate(String value) {

        if (value == null || value.isBlank()) {
            return invalid(value, "Blank account number");
        }

        value = value.trim();

        if (!value.matches("\\d{14}")) {
            return invalid(value, "Account number must contain exactly 14 digits");
        }

        if (value.equals("00000000000000")) {
            return invalid(value, "Account number cannot be all zeros");
        }

        String cif = value.substring(4, 11);

        return new IdentifierResult(
                value,
                IdentifierType.ACCOUNT_NUMBER,
                true,
                null,
                cif
        );
    }

    private IdentifierResult invalid(String value, String error) {
        return new IdentifierResult(
                value,
                IdentifierType.ACCOUNT_NUMBER,
                false,
                error,
                null
        );
    }
}

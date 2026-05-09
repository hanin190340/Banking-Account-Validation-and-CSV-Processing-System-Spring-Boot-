package progressof.Validator;
import progressof.Enum.IdentifierType;
import progressof.model.IdentifierResult;
import progressof.util.IbanChecksumUtil;

public class IbanValidator implements IdentifierValidator {

    @Override
    public IdentifierResult validate(String value) {

        if (value == null || value.isBlank()) {
            return invalid(value, "Blank IBAN");
        }

        value = value.trim().replace(" ", "");

        if (!value.startsWith("OM")) {
            return invalid(value, "Only Oman IBANs are supported");
        }

        if (value.length() != 23) {
            return invalid(value, "Invalid IBAN length");
        }

        if (!value.matches("[A-Z0-9]+")) {
            return invalid(value, "IBAN must contain only letters and digits");
        }

        if (!IbanChecksumUtil.isValid(value)) {
            return invalid(value, "Invalid IBAN checksum");
        }

        String cif = value.substring(11, 18);

        return new IdentifierResult(
                value,
                IdentifierType.IBAN,
                true,
                null,
                cif
        );
    }

    private IdentifierResult invalid(String value, String error) {
        return new IdentifierResult(
                value,
                IdentifierType.IBAN,
                false,
                error,
                null
        );
    }
}
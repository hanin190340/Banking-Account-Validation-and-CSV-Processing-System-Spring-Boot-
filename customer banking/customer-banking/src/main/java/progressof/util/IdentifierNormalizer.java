package progressof.util;
import java.math.BigDecimal;

public class IdentifierNormalizer {

    public static String normalize(String value) {

        if (value == null) {
            return null;
        }

        value = value.trim();

        try {

            if (value.contains("E+") || value.contains("e+")) {

                BigDecimal bd = new BigDecimal(value);

                return bd.toPlainString();
            }

        } catch (Exception ignored) {
        }

        return value;
    }
}
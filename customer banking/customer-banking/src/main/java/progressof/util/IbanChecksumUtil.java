package progressof.util;
import java.math.BigInteger;

public class IbanChecksumUtil {

    public static boolean isValid(String iban) {

        iban = iban.replace(" ", "").toUpperCase();

        String rearranged =
                iban.substring(4) + iban.substring(0, 4);

        StringBuilder numeric = new StringBuilder();

        for (char ch : rearranged.toCharArray()) {

            if (Character.isLetter(ch)) {
                numeric.append(ch - 'A' + 10);
            } else {
                numeric.append(ch);
            }
        }

        BigInteger bigInteger = new BigInteger(numeric.toString());

        return bigInteger.mod(BigInteger.valueOf(97)).intValue() == 1;
    }
}

package progressof.Service;
import progressof.model.IdentifierResult;
import progressof.Enum.OwnershipResult;

import java.util.List;

public class SummaryBuilder {

    public String build(List<IdentifierResult> results,
                        OwnershipResult ownershipResult) {

        StringBuilder sb = new StringBuilder();

        for (IdentifierResult result : results) {

            sb.append("Identifier: ")
                    .append(result.getValue())
                    .append(System.lineSeparator());

            sb.append("Type: ")
                    .append(result.getType())
                    .append(System.lineSeparator());

            sb.append("Valid: ")
                    .append(result.isValid())
                    .append(System.lineSeparator());

            if (result.isValid()) {

                sb.append("Extracted CIF: ")
                        .append(result.getExtractedCif())
                        .append(System.lineSeparator());

            } else {

                sb.append("Error: ")
                        .append(result.getError())
                        .append(System.lineSeparator());
            }

            sb.append("--------------------")
                    .append(System.lineSeparator());
        }

        sb.append("Ownership Result: ")
                .append(ownershipResult.name());

        return sb.toString();
    }
}
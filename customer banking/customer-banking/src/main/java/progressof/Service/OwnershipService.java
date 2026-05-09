package progressof.Service;

import progressof.model.IdentifierResult;
import progressof.Enum.OwnershipResult;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OwnershipService {

    public OwnershipResult determine(List<IdentifierResult> results) {

        List<IdentifierResult> valid =
                results.stream()
                        .filter(IdentifierResult::isValid)
                        .toList();

        if (valid.isEmpty()) {
            return OwnershipResult.NO_VALID_IDENTIFIERS;
        }

        Set<String> cifs =
                valid.stream()
                        .map(IdentifierResult::getExtractedCif)
                        .collect(Collectors.toSet());

        if (cifs.size() == 1) {
            return OwnershipResult.SAME_PERSON;
        }

        return OwnershipResult.MISMATCH;
    }
}

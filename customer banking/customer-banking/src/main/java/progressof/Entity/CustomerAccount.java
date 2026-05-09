package progressof.Entity;
import progressof.Service.SummaryBuilder;
import progressof.model.IdentifierResult;
import progressof.Enum.OwnershipResult;
import progressof.Service.IdentifierClassifier;
import progressof.Service.OwnershipService;

import java.util.ArrayList;
import java.util.List;

public class CustomerAccount {

    public enum IdentifierType {
        IBAN,
        ACCOUNT_NUMBER,
        CIF_NUMBER,
        UNKNOWN
    }

    private final List<String> identifiers;

    private final IdentifierClassifier classifier =
            new IdentifierClassifier();

    private final OwnershipService ownershipService =
            new OwnershipService();

    private CustomerAccount(List<String> identifiers) {

        this.identifiers =
                identifiers != null
                        ? new ArrayList<>(identifiers)
                        : new ArrayList<>();
    }

//    public String getSummary() {
//
//        List<IdentifierResult> results = new ArrayList<>();
//
//        for (String identifier : identifiers) {
//
//            results.add(
//                    classifier.classify(identifier)
//            );
//        }
//
//        OwnershipResult ownershipResult =
//                ownershipService.determine(results);
//
//        return ownershipResult.name();
//    }

    public static CustomerAccount of(List<String> identifiers) {
        return new CustomerAccount(identifiers);
    }
    public String getSummary() {

        List<IdentifierResult> results = new ArrayList<>();

        for (String identifier : identifiers) {

            results.add(
                    classifier.classify(identifier)
            );
        }

        OwnershipResult ownershipResult =
                ownershipService.determine(results);

        SummaryBuilder summaryBuilder =
                new SummaryBuilder();

        return summaryBuilder.build(results, ownershipResult);
    }
}
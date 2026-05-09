package progressof.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Repository;
import progressof.Entity.Request;
import progressof.Enum.OwnershipResult;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, String> {

    List<Request> findByCustomerReference(String reference);

    boolean existsByRequestId(String requestId);
    List<Request> findByOwnershipResult(
            OwnershipResult ownershipResult);

}

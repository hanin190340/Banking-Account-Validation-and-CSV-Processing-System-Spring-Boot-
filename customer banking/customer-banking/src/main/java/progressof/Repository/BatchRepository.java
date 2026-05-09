package progressof.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import progressof.Entity.ImportBatch;

import java.util.UUID;

@Repository
public interface  BatchRepository extends JpaRepository<ImportBatch, UUID> {
}
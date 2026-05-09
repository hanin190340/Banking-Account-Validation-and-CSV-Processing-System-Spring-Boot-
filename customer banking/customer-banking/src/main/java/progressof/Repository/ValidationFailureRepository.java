package progressof.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import progressof.Entity.ValidationFailure;

public interface ValidationFailureRepository
        extends JpaRepository<ValidationFailure, Long> {
}

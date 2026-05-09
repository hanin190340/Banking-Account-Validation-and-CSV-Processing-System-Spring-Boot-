package progressof.Entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "import_batch")
@Data
public class ImportBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID batchId;

    private String fileName;

    private int totalRows;
    private int importedRows;
    private int duplicateRows;
    private int invalidRows;

    private Instant createdAt = Instant.now();
}
package progressof.Entity;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import progressof.Enum.OwnershipResult;
import progressof.Entity.Identifier;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "requests")
public class Request {

    @Id
    private String requestId;

    private String customerReference;

    private Instant submittedAt;

    @Enumerated(EnumType.STRING)
    private OwnershipResult ownershipResult;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private ImportBatch batch;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private List<Identifier> identifiers = new ArrayList<>();
}
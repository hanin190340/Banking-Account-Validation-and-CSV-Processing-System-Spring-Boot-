package progressof.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
import progressof.Enum.IdentifierType;

@Entity
@Table(name = "identifiers")
@Data
public class Identifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rawValue;

    @Enumerated(EnumType.STRING)
    private IdentifierType type;

    private boolean valid;

    private String errorMessage;

    private String extractedCif;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;
}


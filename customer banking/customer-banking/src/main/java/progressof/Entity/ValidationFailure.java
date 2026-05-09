package progressof.Entity;


import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
public class ValidationFailure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String requestId;

    private String identifierValue;

    private String errorMessage;

}
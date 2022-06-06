package fiados.com.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PointRequest {

    private Long idCostumer;

    @Range(min = 1, max = 5, message = "El rango de calificación es de 1 a 5")
    private int point;
}

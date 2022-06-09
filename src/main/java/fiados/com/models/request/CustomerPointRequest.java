
package fiados.com.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter @Setter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPointRequest {
 private Long id_trade;
 
 @Range(min = 1, max = 5, message = "El rango de calificación es de 1 a 5")
 private int points;
}

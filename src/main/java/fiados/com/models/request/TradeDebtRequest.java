
package fiados.com.models.request;


import javax.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TradeDebtRequest {
    private Long id_customer;
    @DecimalMin("10.0") 
    private double totalAmount;
    
}

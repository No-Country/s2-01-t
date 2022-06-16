
package fiados.com.models.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter @Builder
public class CustomerDebtTrade {
    
    private double total_debt;
    private Long id_trade;
    private String company;  
}

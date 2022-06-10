
package fiados.com.models.response;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter @Builder
public class DebtTotalResponse {
    private Long id_customer;
    private double total_debt;
    private Long id_trade;
    private String company;
    private double totaltotal;
}



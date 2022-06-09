package fiados.com.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter @Builder
public class DebtResponse {
    private Long id;
    private double totalAmount;
    private Long id_trade;
    private String company;  
}

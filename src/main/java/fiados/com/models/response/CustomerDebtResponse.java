package fiados.com.models.response;

import fiados.com.models.entity.Trade;
import fiados.com.models.enums.EnumCondition;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class CustomerDebtResponse {
    
    private Long ticket;
    private double totalAmount;
    private LocalDateTime date;
    private EnumCondition conditions;
    private Trade trade;
}

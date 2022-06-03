package fiados.com.models.response;

import fiados.com.models.entity.Customer;
import fiados.com.models.enums.EnumCondition;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter @Setter @Builder
public class TradeDebtResponce {

    private Long ticket;
    private double totalAmount;//cantidad total
    private LocalDateTime date;
    private EnumCondition conditions;
    private Customer customer;
}

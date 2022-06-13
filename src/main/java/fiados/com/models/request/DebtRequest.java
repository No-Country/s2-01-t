package fiados.com.models.request;

import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Trade;
import fiados.com.models.enums.EnumCondition;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
public class DebtRequest {
    private double totalAmount;//cantidad total
    @Enumerated(value = EnumType.STRING)
    private EnumCondition conditions;
    private Customer customer;
    private Trade trade;
}

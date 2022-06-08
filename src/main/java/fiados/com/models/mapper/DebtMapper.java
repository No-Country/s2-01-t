package fiados.com.models.mapper;

import fiados.com.models.entity.Debt;
import fiados.com.models.enums.EnumCondition;
import fiados.com.models.request.DebtRequest;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class DebtMapper {
    
    public Debt toDto(DebtRequest request){
        return Debt.builder()
                .date(LocalDateTime.now())
                .customer(request.getCustomer())
                .trade(request.getTrade())
                .conditions(EnumCondition.ACTIVATED )
                .totalAmount(request.getTotalAmount())
                .build();
    }
}

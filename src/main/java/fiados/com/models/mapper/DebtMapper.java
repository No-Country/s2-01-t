package fiados.com.models.mapper;

import fiados.com.models.entity.Debt;
import fiados.com.models.entity.Trade;
import fiados.com.models.enums.EnumCondition;
import fiados.com.models.request.DebtRequest;
import fiados.com.models.response.DebtResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
  

   public DebtResponse entityToDTO(Debt debt){
       return DebtResponse.builder()
               .id(debt.getId())
               .totalAmount(debt.getTotalAmount())
               .company(debt.getTrade().getCompany_name())
               .id_trade(debt.getTrade().getId())
               .build();
   }
}

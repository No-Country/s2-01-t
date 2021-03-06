package fiados.com.models.mapper;

import fiados.com.models.entity.Debt;
import fiados.com.models.enums.EnumCondition;
import fiados.com.models.request.DebtRequest;
import fiados.com.models.response.CustomerDebtTrade;
import fiados.com.models.response.DebResponseTotal;
import fiados.com.models.response.DebtResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
   public List<DebtResponse> entitySet2DtoList(Set<Debt> debts) {
        List<DebtResponse> debtResponses = new ArrayList<>();
            debts.forEach(debt -> {
                debtResponses.add(entityToDTO(debt));
            });
            return debtResponses;
    }
 
      public CustomerDebtTrade ToTotalDebt(DebResponseTotal debt){
       return null;
   }
  
   
}

package fiados.com.service;

import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Debt;
import fiados.com.models.mapper.DebtMapper;
import fiados.com.models.request.DebRequestTotal;
import fiados.com.models.request.DebtRequest;
import fiados.com.models.response.DebResponseTotal;
import fiados.com.models.response.DebtResponse;
import fiados.com.models.response.TradeDebtResponce;
import fiados.com.repository.DebtRepository;
import fiados.com.service.abstraction.DebtsService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebtsServiceImpl implements DebtsService {
      
    
    @Autowired
    private DebtRepository debtRepository;
    @Autowired
    private DebtMapper debtMapper;
    
    @Override
    public List<Debt> findByCustomer(Customer  customer) {
        
    return null;
    }

    @Override
    public TradeDebtResponce findByTrade(String id) {
       return null;   }
   
    @Override
    public Debt add(DebtRequest debtrequest){
        try{
            
             return debtRepository.save(debtMapper.toDto(debtrequest));
        }catch(Exception e){
            throw new RuntimeException("Problemas  ejecutar el ticket."+e.getMessage());
        }
    }

    @Override
    public List<DebtResponse> findAllDebt() {      
        return debtRepository.findAll().stream()
                .map( i -> debtMapper.entityToDTO(i))
                .collect(Collectors.toList());  
    }

    @Override
    public DebResponseTotal getTotal(DebRequestTotal request) {
        List<Debt> debts = debtRepository.findByCustomerIdAndTradeIdAndConditions(request.getCustomerId(),request.getTradeId(),request.getConditions());
        double suma = debts.stream().parallel().mapToDouble(Debt::getTotalAmount).sum();
        return DebResponseTotal.builder()
                .totalAmount(suma)
                .tradeId(request.getTradeId())
                .customerId(request.getCustomerId())
                .build();
    }
}

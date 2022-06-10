package fiados.com.service;

import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Debt;
import fiados.com.models.enums.EnumCondition;
import fiados.com.models.mapper.DebtMapper;
import fiados.com.models.request.DebtRequest;
import fiados.com.models.response.DebtResponse;
import fiados.com.models.response.DebtTotalResponse;
import fiados.com.models.response.TradeDebtResponce;
import fiados.com.repository.DebtRepository;
import fiados.com.repository.UserRepository;
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
    @Autowired
    private UserRepository userRepository;
    
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
    public  List<DebtTotalResponse> findDebtTotalResponse(Long id) {
       boolean exist=userRepository.existsById(id);       
    return debtRepository.findByCustomerIdAndTradeIdAndConditions(3L,1L,EnumCondition.ACTIVATED).stream()
                .map( i -> debtMapper.debtTotalToDTO(i))
                .collect(Collectors.toList());
    }
//    
 
}

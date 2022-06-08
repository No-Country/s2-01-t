package fiados.com.service;

import fiados.com.models.entity.Debt;
import fiados.com.models.mapper.DebtMapper;
import fiados.com.models.request.DebtRequest;
import fiados.com.models.response.CustomerDebtResponse;
import fiados.com.models.response.TradeDebtResponce;
import fiados.com.repository.DebtRepository;
import fiados.com.service.abstraction.DebtsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DebtsServiceImpl implements DebtsService {
      
    
    @Autowired
    private DebtRepository debtRepository;
    @Autowired
    private DebtMapper debtMapper;
    
    @Override
    public CustomerDebtResponse findByCustomer(String id) {
        
     return null;    }

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
}

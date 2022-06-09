package fiados.com.service;

import fiados.com.models.entity.Comment;
import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Debt;
import fiados.com.models.entity.Point;
import fiados.com.models.entity.Trade;
import fiados.com.models.mapper.CustomerMapper;
import fiados.com.models.request.CommentRequest;
import fiados.com.models.request.CommentTradeRequest;
import fiados.com.models.request.CustomerPointRequest;
import fiados.com.models.request.CustomerRequest;
import fiados.com.models.response.CustomerComment;
import fiados.com.models.response.CustomerResponse;
import fiados.com.models.response.PointResponse;
import fiados.com.repository.CustomerRepository;
import fiados.com.repository.UserRepository;
import fiados.com.service.abstraction.CommentService;
import fiados.com.service.abstraction.CustomerService;
import fiados.com.service.abstraction.DebtsService;
import fiados.com.service.abstraction.PointService;
import fiados.com.service.abstraction.TradeService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CommentService commentService;
    @Autowired 
    private UserRepository userRepository;
    @Autowired
    private TradeService  tradeService;
    @Autowired 
    private DebtsService debtService;
    @Autowired
    private PointService pointService;
    
    private static final String ERROR_USER_NOT_FOUND = "the searched user does not exist";
    
    
    @Override
    public Customer getInfoUser() {
      Object userInstance = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
           if(userInstance instanceof Customer){
            String username =  ((Customer) userInstance).getUsername();
        
           }
        }catch (Exception e) {
           throw new UsernameNotFoundException("User not found");                   
        }  
        return (Customer) userRepository.findByEmail(userInstance.toString());
    }

    @Override
    public void delete(Long id) {
         Customer customer = getById(id);
         customer.setSoftDelete(true);         
         customerRepository.save(customer);
    }

    @Override
    @Transactional
    public CustomerResponse update(Long id, CustomerRequest request) {
        try {                
         Customer customerSaved=customerMapper.updateDto(getById(id), request);
         return customerMapper.entityToDTO(userRepository.save(customerSaved));
            
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found"+e.getMessage()); 
        }
       
       }

    @Override
    public Customer getById(Long id) {
       return  customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
               ERROR_USER_NOT_FOUND));        
    }
    
    @Override
    public CustomerResponse  findById(Long id){        
        return customerMapper.entityToDTO(getById(id));
    }
            
    @Override
    public List<CustomerResponse> getAllUser() {
         return customerRepository.findAll().stream()
                .map( i -> customerMapper.entityToDTO(i) )
                .collect(Collectors.toList()); 
    }

    @Override
    public CustomerComment commentUser(CommentTradeRequest comment) {
        Customer customer=getInfoUser();   
       List<Comment> comments=new ArrayList<>();      
       Comment response=commentService.addComment(
               CommentRequest.builder()
                .comment(comment.getComment())
                .customer(customer)
                .trade(tradeService.getTrade(comment.getId_trade()))
                .build());
        comments.add(response);
        customer.setComments(comments);       
        
        return customerMapper.dtoCustomerComment( customerRepository.save(customer), response);
    }
    @Override
    public List<Debt> customerTotalAmount(){
         Customer customer=getInfoUser();     
         List<Debt> listDebt=new ArrayList<>();
         listDebt=debtService.findByCustomer(customer);
         return listDebt;
    }

    @Override
    public void customerDebt(Debt debt, Customer customer) {
        Set<Debt> debts=new HashSet(); 
        debts.add(debt);
        customer.setDebts(debts);
         try{
            customerRepository.save(customer);
        }catch(Exception e){
            throw new RuntimeException("error saving merchant data.");
        }
    }
    @Override
    public PointResponse customerPoint(CustomerPointRequest request){
        System.out.println("ENTRA ACA");
         Customer customer=getInfoUser(); 
          System.out.println("Customer: "+customer.getFirstName().toUpperCase() +" Puntos: "+request.getPoints());
         Trade trade = tradeService.getTrade(request.getId_trade());
           
            
            Point point = Point.builder()
                    .idCostumer(customer.getId())
                    .idTrade(trade.getId())
                    .point(request.getPoints())
                    .build();
            trade.addPoint(point);
            System.out.println("Trade: "+ trade.getFirstName());
            try{
               if(pointService.addPointcustomer(point)){
                   tradeService.tradeAddPoint(trade, point);
               }
                return PointResponse.builder()
                        .id(point.getId())
                        .idCostumer(customer.getId())
                        .idTrade(trade.getId())
                        .point_client(point.getPoint())
                        .build();       
             }catch(Exception e){
            throw new RuntimeException("error saving client data.");
           } 
           
           
       
    }
}

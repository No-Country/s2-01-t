package fiados.com.service;

import fiados.com.models.entity.Comment;
import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Trade;
import fiados.com.models.mapper.CustomerMapper;
import fiados.com.models.request.CommentRequest;
import fiados.com.models.request.CommentTradeRequest;
import fiados.com.models.request.CustomerRequest;
import fiados.com.models.response.CustomerComment;
import fiados.com.models.response.CustomerResponse;
import fiados.com.repository.CustomerRepository;
import fiados.com.repository.UserRepository;
import fiados.com.service.abstraction.CommentService;
import fiados.com.service.abstraction.CustomerService;
import fiados.com.service.abstraction.TradeService;
import java.util.ArrayList;
import java.util.List;
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
  

}

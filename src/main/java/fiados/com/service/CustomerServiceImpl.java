package fiados.com.service;

import fiados.com.models.entity.Comment;
import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Debt;
import fiados.com.models.entity.Point;
import fiados.com.models.entity.Trade;
import fiados.com.models.enums.EnumCondition;
import fiados.com.models.mapper.CustomerMapper;
import fiados.com.models.mapper.PointMapper;
import fiados.com.models.request.CommentRequest;
import fiados.com.models.request.CommentTradeRequest;
import fiados.com.models.request.CustomerPointRequest;
import fiados.com.models.request.CustomerRequest;
import fiados.com.models.request.DebRequestTotal;
import fiados.com.models.response.CustomerAbsResponse;
import fiados.com.models.response.CustomerComment;
import fiados.com.models.response.CustomerResponse;
import fiados.com.models.response.DebResponseTotal;
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
    private TradeService tradeService;
    @Autowired
    private DebtsService debtService;
    @Autowired
    private PointService pointService;
    @Autowired
    private PointMapper pointMapper;

    private static final String ERROR_USER_NOT_FOUND = "the searched user does not exist";

    @Override
    public Customer getInfoUser() {
        Object userInstance = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            if (userInstance instanceof Customer) {
                String username = ((Customer) userInstance).getUsername();

            }
        } catch (Exception e) {
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
            Customer customerSaved = customerMapper.updateDto(getById(id), request);
            return customerMapper.entityToDTO(userRepository.save(customerSaved));

        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found" + e.getMessage());
        }

    }

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                ERROR_USER_NOT_FOUND));
    }

    @Override
    public CustomerResponse findById(Long id) {
        return customerMapper.entityToDTO(getById(id));
    }

    @Override
    public List<CustomerAbsResponse> getAllUser() {
        List<Customer> list=customerRepository.findAll();
     
        
        List<CustomerAbsResponse> response=new ArrayList<>();
        if(list.isEmpty()){
              throw new RuntimeException("List client empty.");
        }
        for (Customer customer : list) {
            List<DebResponseTotal> debtsTotal=new ArrayList<>();
            
            if(!customer.getDebts().isEmpty()){
            List<Long> trades=debtService.debtsTotal(customer.getDebts());
          
              for (Long trade : trades) {             
            debtsTotal.add(debtService.getTotal( DebRequestTotal.builder()
                    .customerId(customer.getId())
                    .conditions(EnumCondition.ACTIVATED)
                    .tradeId(trade)
                    .build()));
            }            
        response.add(customerMapper.entityTodDtoAbs(customer, debtsTotal));
        }else{
                 response.add(customerMapper.entityTodDtoAbs(customer,debtsTotal));
              }
           
        }
       return response;
        
    }

    @Override
    public CustomerComment commentUser(CommentTradeRequest comment) {
        Customer customer = getInfoUser();
        List<Comment> comments = customer.getComments();
        Comment response = commentService.addComment(
                CommentRequest.builder()
                        .comment(comment.getComment())
                        .customer(customer)
                        .trade(tradeService.getTrade(comment.getId_trade()))
                        .build());
        comments.add(response);
        customer.setComments(comments);

        return customerMapper.dtoCustomerComment(customerRepository.save(customer), response);
    }

    @Override
    public List<Debt> customerTotalAmount() {
        Customer customer = getInfoUser();
        List<Debt> listDebt = new ArrayList<>();
        listDebt = debtService.findByCustomer(customer);
        return listDebt;
    }

    @Override
    public void customerDebt(Debt debt, Customer customer) {
        Set<Debt> debts = new HashSet();
        debts.add(debt);
        customer.setDebts(debts);
        try {
            customerRepository.save(customer);
        } catch (Exception e) {
            throw new RuntimeException("error saving merchant data.");
        }
    }
      
    @Override
    public PointResponse customerPoint(CustomerPointRequest request) {

        Customer customer = getInfoUser();
        Trade trade = tradeService.getTrade(request.getId_trade());

        Point point = Point.builder()
                .idCostumer(customer.getId())
                .idTrade(trade.getId())
                .point(request.getPoints())
                .build();
        try {
            tradeService.tradeAddPoint(trade, pointService.addPointCustomer(point));
            return pointMapper.DTO2Entity(point);
        } catch (Exception e) {
            throw new RuntimeException("error saving client data.");
        }
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}

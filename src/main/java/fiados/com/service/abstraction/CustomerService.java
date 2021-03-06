package fiados.com.service.abstraction;

import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Debt;
import fiados.com.models.request.CommentTradeRequest;
import fiados.com.models.request.CustomerPointRequest;
import fiados.com.models.request.CustomerRequest;
import fiados.com.models.response.CustomerAbsResponse;
import fiados.com.models.response.CustomerComment;
import fiados.com.models.response.CustomerResponse;
import fiados.com.models.response.PointResponse;
import java.util.List;

public interface CustomerService{
     public Customer getInfoUser();        
    public void delete(Long id);   
    public CustomerResponse update(Long id, CustomerRequest request);    
    public Customer getById(Long id);
    public CustomerResponse findById(Long id);
    public List<CustomerAbsResponse> getAllUser();
    //comments
    public CustomerComment commentUser(CommentTradeRequest comment);
    //debt
    public List<Debt> customerTotalAmount();
    public void customerDebt(Debt debt, Customer custormer);
    //point 
    PointResponse customerPoint(CustomerPointRequest request);

    void save(Customer customer);

}

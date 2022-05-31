package fiados.com.service.abstraction;

import fiados.com.models.entity.Customer;
import fiados.com.models.request.CustomerRequest;
import fiados.com.models.response.CustomerResponse;
import java.util.List;

public interface CustomerService{
     public Customer getInfoUser();        
    public void delete(Long id);   
    public CustomerResponse update(Long id, CustomerRequest request);    
    public CustomerResponse getById(Long id);
    public List<CustomerResponse> getAllUser();
}

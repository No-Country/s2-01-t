package fiados.com.service;

import fiados.com.models.entity.Customer;
import fiados.com.models.mapper.CustomerMapper;
import fiados.com.models.request.CustomerRequest;
import fiados.com.models.response.CustomerResponse;
import fiados.com.repository.CustomerRepository;
import fiados.com.service.abstraction.CustomerService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;
    
    private static final String ERROR_USER_NOT_FOUND = "the searched user does not exist";
    
    
    @Override
    public Customer getInfoUser() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Long id) {
         Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                ERROR_USER_NOT_FOUND));
         customer.setSoftDelete(true);         
         customerRepository.save(customer);
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest request) {
         Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                ERROR_USER_NOT_FOUND));         
         Customer customerSaved=customerMapper.updateDto(customer, request);
         return customerMapper.entityToDTO(customerRepository.save(customerSaved));
       }

    @Override
    public CustomerResponse getById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                ERROR_USER_NOT_FOUND));
        return customerMapper.entityToDTO(customer);
    }

    @Override
    public List<CustomerResponse> getAllUser() {
         return customerRepository.findAll().stream()
                .map( i -> customerMapper.entityToDTO(i) )
                .collect(Collectors.toList()); 
    }

   

 
}

package fiados.com.controller;

import fiados.com.models.request.CustomerRequest;
import fiados.com.models.response.CustomerComment;
import fiados.com.models.response.CustomerResponse;
import fiados.com.service.abstraction.CustomerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CustomerController {
    
    private final CustomerService customerService;
    
    @GetMapping("/all")
    public List<CustomerResponse> getAllCustomers(){
        return customerService.getAllUser();
    }
    
    @GetMapping("{id}")
    public CustomerResponse getCustomer(@PathVariable Long id){
        return customerService.getById(id);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody CustomerRequest request){
        customerService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        customerService.delete(id);
    }
    
    @PostMapping("/customer_comment")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerComment commentCustomer(@RequestBody String comment){
        return  customerService.commentUser(comment);
    }
    
}

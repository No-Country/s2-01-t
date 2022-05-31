package fiados.com.models.mapper;

import fiados.com.models.entity.Customer;
import fiados.com.models.request.CustomerRequest;
import fiados.com.models.response.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {


    public CustomerResponse entityToDTO(Customer request) {
      
        return CustomerResponse.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dni(request.getDni())
                .email(request.getEmail())
                .build();
    }

    public Customer updateDto(Customer user, CustomerRequest request){
        user.setDni(request.getDni());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(request.getRole());
        user.setCity(request.getCity());
        user.setDirection(request.getDirection());
        return user;
    }
}

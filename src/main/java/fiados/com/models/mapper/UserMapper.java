package fiados.com.models.mapper;

import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Trade;
import fiados.com.models.enums.EnumCondition;
import fiados.com.models.request.UserRegister;
import fiados.com.models.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    public Customer entityToDTO(UserRegister request) {
        Customer user = new Customer();
        user.setDni(request.getDni());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(request.getRole());
        user.setCity(request.getCity());
        user.setCountry(request.getCountry());
        user.setAdress(request.getAdress());
        return user;
    }

    public UserResponse dtoToEntity(Customer entity) {
        UserResponse response = new UserResponse();
        response.setId(entity.getId());
        response.setEmail(entity.getEmail());
        return response;
    }

    public UserResponse dtoToEntityTrade(Trade entity) {
        UserResponse response = new UserResponse();
         response.setId(entity.getId());
        response.setEmail(entity.getEmail());
        return response;
    }

    public Trade entityTradeToDTO(UserRegister request) {
        Trade user = new Trade();
        user.setDni(request.getDni());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(request.getRole());
        user.setCity(request.getCity());
        user.setAdress(request.getAdress());
        user.setCountry(request.getCountry());
        user.setStatus(EnumCondition.PAUSED);
        user.setCompany_name(request.getCompany_name());
        return user;
    }
}

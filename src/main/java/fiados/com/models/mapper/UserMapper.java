package fiados.com.models.mapper;

import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Trade;
import fiados.com.models.entity.User;
import fiados.com.models.enums.EnumCondition;
import fiados.com.models.request.UserRegister;
import fiados.com.models.response.UserFilterResponse;
import fiados.com.models.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private BranchMapper branchMapper;
    @Autowired
    private PointMapper pointMapper;

    @Autowired
    private DebtMapper debtMapper;

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

    public UserFilterResponse entityToDTOFilter(Trade user) {
        UserFilterResponse response = new UserFilterResponse();
        response.setId(user.getId());
        response.setCity(user.getCity());
        response.setCountry(user.getCountry());
        response.setDni(user.getDni());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setRole(user.getRole());
        response.setAdress(user.getAdress());
        response.setCompany_name(user.getCompany_name());
        response.setBranchResponseList(user.getBranchList().stream().map(branch ->
                branchMapper.entity2DTOFilter(branch)).collect(Collectors.toList()));
        response.setPointResponses(user.getPoints().stream().map(point ->
                pointMapper.DTO2Entity(point)).collect(Collectors.toList()));
        response.setDebtResponseList(user.getDebts().stream().map(debt ->
                debtMapper.entityToDTO(debt)).collect(Collectors.toList()));
        return response;
    }

    public UserFilterResponse entityToDTOFilterCustomer(Customer user) {
        UserFilterResponse response = new UserFilterResponse();
        response.setId(user.getId());
        response.setCity(user.getCity());
        response.setCountry(user.getCountry());
        response.setDni(user.getDni());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setRole(user.getRole());
        response.setAdress(user.getAdress());
        response.setPointResponses(user.getPoints().stream().map(point ->
                pointMapper.DTO2Entity(point)).collect(Collectors.toList()));
        response.setDebtResponseList(user.getDebts().stream().map(debt ->
                debtMapper.entityToDTO(debt)).collect(Collectors.toList()));
        return response;
    }
}

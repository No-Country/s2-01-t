package fiados.com.models.mapper;

import fiados.com.models.entity.Comment;
import fiados.com.models.entity.Customer;
import fiados.com.models.request.CustomerRequest;
import fiados.com.models.response.CustomerComment;
import fiados.com.models.response.CustomerResponse;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    @Autowired 
    private DebtMapper debtMapper;
    
    @Autowired
    private PointMapper pointMapper;
    @Autowired 
    private CommentMapper commentMapper;

    public CustomerResponse entityToDTO(Customer request) {
      
        return CustomerResponse.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dni(request.getDni())
                .email(request.getEmail())
                .role(request.getRole())
                .adress(request.getAdress())
                .city(request.getCity())
                .country(request.getCountry())
                .debtResponseList(request.getDebts().stream()
                        .map( i -> debtMapper.entityToDTO(i) )
                        .collect(Collectors.toList()) )
                .pointResponses(request.getPoints().stream()
                        .map(i->pointMapper.DTO2Entity(i))
                        .collect(Collectors.toList()))
                .commentListResponses(request.getComments().stream()
                        .map(i->commentMapper.toListDto(i))
                        .collect(Collectors.toList()))
                .build();
    }

    public Customer updateDto(Customer user, CustomerRequest request){
        user.setDni(request.getDni());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(request.getRole());
        user.setCity(request.getCity());
        user.setAdress(request.getAdress());
        user.setCountry(request.getCountry());
        return user;
    }
    
    public CustomerComment dtoCustomerComment(Customer customer, Comment comment){
        return CustomerComment.builder()
                .id_customer(customer.getId())
                .first_name(customer.getFirstName())
                .last_name(customer.getLastName())               
                .date(comment.getDate().toLocalDate())
                .hour(comment.getDate().toLocalTime())
                .comment(comment.getComment())
                .id_trade(comment.getTrade().getId())
                .company(comment.getTrade().getCompany_name())                
                .build();
    }
}

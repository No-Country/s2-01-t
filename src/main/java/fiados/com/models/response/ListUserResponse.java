package fiados.com.models.response;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Builder
@Setter
@Getter
public class ListUserResponse {
    private Long id;    
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String dni;
    private String city;
    private String adress;
    private String country;
}

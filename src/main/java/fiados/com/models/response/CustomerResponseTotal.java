package fiados.com.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CustomerResponseTotal {

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

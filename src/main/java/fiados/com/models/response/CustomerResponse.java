
package fiados.com.models.response;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
@Getter @Setter
@Builder
public class CustomerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String dni;
    private String city;
    private String direction;
}
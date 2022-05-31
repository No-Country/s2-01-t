
package fiados.com.models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
@Getter @Setter
@Builder
public class CustomerRequest {

    @NotBlank(message = "First Name cannot be empty.")
    private String firstName;
    @NotBlank(message = "Last Name cannot be empty.")
    private String lastName;
    @NotBlank(message = "Email cannot be empty.")
    private String email; 
    private String role;
    @NotBlank(message = "DNI cannot be empty.")
    private String dni;
    private String city;
    private String direction;
    
}


package fiados.com.models.request;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

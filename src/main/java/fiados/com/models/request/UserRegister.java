package fiados.com.models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserRegister {
    
    @NotBlank(message = "First Name cannot be empty.")
    protected String firstName;
    @NotBlank(message = "Last Name cannot be empty.")
    protected String lastName;
    @NotBlank(message = "Email cannot be empty.") 
    protected String email; 

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, max = 25, message = "Password should have at least 8 characters")
    protected String password;
    @NotBlank(message = "Role cannot be empty.")
    protected String role;
    @NotBlank(message = "Dni cannot be empty.")
    protected String dni;

    protected String city;

    protected String direction;  
    
}

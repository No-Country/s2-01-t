package fiados.com.models.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    private String firstName;
    @NotBlank(message = "Last Name cannot be empty.")
    private String lastName;
    @NotBlank(message = "Email cannot be empty.") 
    private String email; 

    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, max = 25, message = "Password should have at least 8 characters")
    private String password;
    @NotBlank(message = "Role cannot be empty.")
    private String role;
    @NotBlank(message = "Dni cannot be empty.")
    private String dni;
    @NotBlank
    @NotEmpty(message = "You must entnomer company name")
    @Size(min = 3, max = 100, message = "Company name must be between 3 and 100 characters long")
    private String company_name;
    protected String city;
    protected String adress;
    protected String country; 
    
}

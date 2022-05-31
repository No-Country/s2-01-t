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
public class UserRequest {
 @NotBlank(message = "First Name cannot be empty.")
    private String firstName;
    @NotBlank(message = "Last Name cannot be empty.")
    private String lastName;
    @NotBlank(message = "Password cannot be empty.")
    @Size(min = 8, max = 250, message = "Password should have at least 8 characters")
    private String password;
    private String dni;
    private String city;
    private String direction;


}

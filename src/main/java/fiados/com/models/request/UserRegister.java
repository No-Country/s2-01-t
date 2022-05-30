package fiados.com.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class UserRegister {

    private String firstName;
    private String lastName;
    private String email;
    private String dni;
    private String role;
    private String city;
    private String direction;
    private String password;


}

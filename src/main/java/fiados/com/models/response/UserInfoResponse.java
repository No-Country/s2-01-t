
package fiados.com.models.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter @Setter
@Builder
public class UserInfoResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String dni;
    private String role;
    private String city;
    private String adress;
    private String country;

}

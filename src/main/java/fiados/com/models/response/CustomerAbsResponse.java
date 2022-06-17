
package fiados.com.models.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
@Getter @Setter
@Builder
public class CustomerAbsResponse {
     private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String dni;
    private String city;
    private String adress;
    private String country;
    private int pointResponses; //total puntos
    private List<DebResponseTotal> debt_total; //deuda total
    private int commentListResponses; //cantidad Msj
}

package fiados.com.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class TradeUpdateResponse {

    private Long id;
    private String email;
    private String dni;
    private String city;
    private String adress;
    private String country;
    private String firstName;
    private String lastName;
    private String company_name;
}

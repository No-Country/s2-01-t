package fiados.com.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TradeBranchResponse {

    private Long id;
    private String email;
    private String role;
    private String dni;
    protected String city;
    protected String adress;
    protected String country;

}

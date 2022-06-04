
package fiados.com.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class TradeResponse {

    private Long id;
    private String email;
    private String role;
    private String dni;
    private String city;
    private String adress;
    private String country;
    private String firstName;
    private String lastName;
    private String company_name;
    private List<BranchResponse> branchResponseList;
    private List<PointResponse> pointResponses;

    
}

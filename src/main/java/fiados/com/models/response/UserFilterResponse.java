package fiados.com.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFilterResponse {
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
    private List<DebtResponse> debtResponseList;

    public UserFilterResponse(Long id, String firstName, String lastName, String dni, String email, String adress, String country, String role, String city) {
    }
}

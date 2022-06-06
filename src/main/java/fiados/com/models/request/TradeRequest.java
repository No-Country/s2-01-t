package fiados.com.models.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Getter @Setter
public class TradeRequest {

    @NotBlank
    @NotEmpty(message = "You must entnomer company name")
    @Size(min = 3, max = 100, message = "Company name must be between 3 and 100 characters long")
    private String company_name;
    @NotBlank(message = "First Name cannot be empty.")
    private String firstName;
    @NotBlank(message = "Last Name cannot be empty.")
    private String lastName;
    @NotBlank(message = "Email cannot be empty.")
    private String email;
    @NotBlank(message = "DNI cannot be empty.")
    private String dni;
    protected String city;
    protected String adress;
    protected String country;

    
}

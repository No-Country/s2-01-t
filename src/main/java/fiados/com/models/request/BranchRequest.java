package fiados.com.models.request;

import fiados.com.models.entity.Trade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class BranchRequest {


    private String descriptions;

    private String direccion;

    //private Trade trade;

    private Long idCategory;
}

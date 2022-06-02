package fiados.com.models.response;

import fiados.com.models.entity.Trade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BranchResponse {


    private String descriptions;

    private String direccion;

    private TradeBranchResponse trade;
}

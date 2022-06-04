package fiados.com.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PointResponse {

    private Long idCostumer;

    private Long idTrade;

    private int point_client;


}

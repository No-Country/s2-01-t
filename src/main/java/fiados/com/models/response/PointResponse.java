package fiados.com.models.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PointResponse {

    private Long id;

    private Long idCostumer;

    private Long idTrade;

    private int point_client;


}

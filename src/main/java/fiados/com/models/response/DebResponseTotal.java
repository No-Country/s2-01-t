package fiados.com.models.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Setter
@Getter
public class DebResponseTotal {

    private double totalAmount;//cantidad total
    private long customerId;
    private long tradeId;
}

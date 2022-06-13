package fiados.com.models.response;

import fiados.com.models.enums.EnumCondition;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Setter
@Getter
public class DebResponseTotal {

    private double totalAmount;//cantidad total
    private long customerId;
    private long tradeId;
}

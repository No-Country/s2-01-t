package fiados.com.models.request;

import fiados.com.models.enums.EnumCondition;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class DebRequestTotal {

    //private double totalAmount;//cantidad total
    private Long customerId;
    private Long tradeId;
    @Enumerated(value = EnumType.STRING)
    private EnumCondition conditions;
}

package fiados.com.models.response;

import fiados.com.models.enums.EnumCondition;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class DebtCustomerResponse {
    private Long id;
    private LocalDate date;
    private LocalTime hour;
    private double totalAmount;
    private EnumCondition conditions;
    private Long customer_id;
    private String first_name;
    private String last_name; 
    private Long trade_id;
    private String company;
    
}

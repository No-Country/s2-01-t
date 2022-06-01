package fiados.com.models.response;

import java.time.LocalDate;

import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter @Setter
@Builder
public class CustomerComment {
     private Long id_customer;
  private String first_name;
  private String last_name;
  private LocalDate date;
  private LocalTime hour;
  private String comment;    
    
}

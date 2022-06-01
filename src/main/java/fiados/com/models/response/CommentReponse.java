package fiados.com.models.response;


import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
@Builder
public class CommentReponse {
  
  private Long id_customer;
  private String first_name;
  private String last_name;
  private LocalDateTime date;
  private String comment;    
    
}

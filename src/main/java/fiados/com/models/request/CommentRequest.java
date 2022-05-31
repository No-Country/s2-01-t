
package fiados.com.models.request;


import fiados.com.models.entity.Customer;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter @Builder

public class CommentRequest {
   
    @NotBlank
    @Size(min = 3, max = 100, message = "Comment must be between 3 and 100 characters long")
    private String comment;
    
    private Customer customer;
}

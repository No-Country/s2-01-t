
package fiados.com.models.response;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
@Getter
@Setter
@Builder
public class CommentResponse {

    private String comment;
    private String first_name;
    private String last_name;
    private LocalDate date;

}

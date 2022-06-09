
package fiados.com.models.response;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter @Setter @Builder
public class CommentListResponse {
    private Long id;
    private Long id_rte;
    private String comment;
    private String first_name;
}

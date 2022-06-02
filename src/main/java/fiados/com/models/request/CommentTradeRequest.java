
package fiados.com.models.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Getter @Setter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentTradeRequest {
    private String comment;
    private Long id_trade;
}

package fiados.com.models.mapper;

import fiados.com.models.entity.Comment;
import fiados.com.models.request.CommentRequest;
import fiados.com.models.response.CommentListResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
   
    
    public Comment commentToDto (CommentRequest request){
        return Comment.builder()
                .comment(request.getComment())
                .customer(request.getCustomer())
                .date(LocalDateTime.now() )
                .trade(request.getTrade())
                .build();
    }
    public CommentListResponse toListDto(Comment comments){
        return CommentListResponse.builder()
                .id(comments.getId())
                .comment(comments.getComment())
                .id_rte(comments.getTrade().getId())
                .first_name(comments.getTrade().getCompany_name())
                .build();
    }
}

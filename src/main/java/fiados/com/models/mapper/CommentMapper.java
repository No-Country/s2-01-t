package fiados.com.models.mapper;

import fiados.com.models.entity.Comment;
import fiados.com.models.request.CommentRequest;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
   
    
    public Comment commentToDto (CommentRequest request){
        return Comment.builder()
                .comment(request.getComment())
                .customer(request.getCustomer())
                .date(LocalDateTime.now() )
                .build();
    }
}

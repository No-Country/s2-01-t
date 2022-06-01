package fiados.com.service.abstraction;

import fiados.com.models.entity.Comment;
import fiados.com.models.request.CommentRequest;


public interface CommentService {
    Comment addComment(CommentRequest request);
}

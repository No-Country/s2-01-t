package fiados.com.service.abstraction;

import fiados.com.models.request.CommentRequest;
import fiados.com.models.response.CommentResponse;

public interface CommentService {
    CommentResponse addComment(CommentRequest request);
}

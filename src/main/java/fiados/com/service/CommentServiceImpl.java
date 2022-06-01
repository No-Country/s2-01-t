package fiados.com.service;

import fiados.com.models.request.CommentRequest;
import fiados.com.models.response.CommentResponse;
import fiados.com.repository.CommentRepository;
import fiados.com.service.abstraction.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public CommentResponse addComment(CommentRequest request) {
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

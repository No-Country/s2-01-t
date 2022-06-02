package fiados.com.service;

import fiados.com.models.entity.Comment;
import fiados.com.models.mapper.CommentMapper;
import fiados.com.models.request.CommentRequest;
import fiados.com.repository.CommentRepository;
import fiados.com.service.abstraction.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentMapper commentMapper;

 
    @Override
    public Comment addComment(CommentRequest request) {         
        return   commentRepository.save(commentMapper.commentToDto(request));
    }
    
}

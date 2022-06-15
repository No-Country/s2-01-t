package fiados.com.service.abstraction;

import fiados.com.models.entity.User;
import fiados.com.models.request.UserRequest;
import fiados.com.models.response.UserFilterResponse;
import fiados.com.models.response.UserResponse;
import java.util.List;
import javassist.NotFoundException;
import javax.persistence.EntityNotFoundException;

public interface UserService {
    User getInfoUser() throws NotFoundException;
    void delete(Long id)throws EntityNotFoundException;
    UserResponse update(Long id, UserRequest request)throws NotFoundException;
    UserResponse getById(Long id);
    List<UserResponse> getAllUser();

    List<UserFilterResponse> searchUsers(String term);

}

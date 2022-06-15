package fiados.com.service.abstraction;

import fiados.com.models.entity.User;
import fiados.com.models.request.AuthRequest;
import fiados.com.models.request.UserRegister;
import fiados.com.models.response.AuthResponse;
import fiados.com.models.response.UserFilterResponse;
import fiados.com.models.response.UserResponse;

import java.util.List;

public interface AuthService {
    UserResponse register(UserRegister request);

    AuthResponse login(AuthRequest request);

    User getInfoUser();

    List<UserFilterResponse> searchUsers(String term);
}

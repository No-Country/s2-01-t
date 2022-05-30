package fiados.com.service.abstraction;

import fiados.com.models.request.AuthRequest;
import fiados.com.models.request.UserRegister;
import fiados.com.models.response.AuthResponse;
import fiados.com.models.response.UserResponse;

public interface AuthService {
    UserResponse register(UserRegister request);

    AuthResponse login(AuthRequest request);
}

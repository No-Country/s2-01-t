package fiados.com.service.abstraction;

import fiados.com.models.response.ListUserResponse;
import java.util.List;

public interface UserAdminService {

    List<ListUserResponse>  getAllUser();
}

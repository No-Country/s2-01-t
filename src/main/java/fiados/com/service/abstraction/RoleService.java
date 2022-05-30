package fiados.com.service.abstraction;

import fiados.com.models.entity.Role;

public interface RoleService {
    Role findBy(String fullRoleName);
}

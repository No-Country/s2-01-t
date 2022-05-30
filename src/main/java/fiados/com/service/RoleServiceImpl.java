package fiados.com.service;

import fiados.com.models.entity.Role;
import fiados.com.repository.RoleRepository;
import fiados.com.service.abstraction.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Role findBy(String name) {
        return roleRepository.findByName(name);
    }
}

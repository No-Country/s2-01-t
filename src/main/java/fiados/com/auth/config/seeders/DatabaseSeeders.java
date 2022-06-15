package fiados.com.auth.config.seeders;

import fiados.com.models.entity.Category;
import fiados.com.models.entity.Role;
import fiados.com.models.enums.EnumRoles;
import fiados.com.repository.CategoryRepository;
import fiados.com.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class DatabaseSeeders {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CategoryRepository categoryRepository;
            

    @EventListener
    public void seed(ContextRefreshedEvent event){
        List<Role> roleList = roleRepository.findAll();
        if(roleList.isEmpty()){
            createRoles();
            createCategory();
        }
    }

    private void createRoles() {
        createRole(1L, EnumRoles.USER);
        createRole(2L, EnumRoles.CUSTOMER);
        createRole(3L, EnumRoles.MERCHANT);
    }

    private void createRole(Long id, EnumRoles enumRoles) {
        Role role = new Role();
        role.setId(id);
        role.setName(enumRoles.getFullRoleName());
        role.setDescription(enumRoles.getName());
        role.setTimestamp(new Timestamp(System.currentTimeMillis()));
        roleRepository.save(role);
    }
    
    private void createCategory(){        
       
        categoryRepository.save( new Category(1L,"Calzados","Tienda de calzados",false));
        categoryRepository.save( new Category(2L,"Comida","Tienda de Comidas",false));
        categoryRepository.save( new Category(3L,"Chocolate","Tienda de chocolates",false));
    }
}

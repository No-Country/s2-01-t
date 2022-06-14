
package fiados.com.controller;

import fiados.com.models.response.CustomerResponse;
import fiados.com.models.response.ListUserResponse;
import fiados.com.service.abstraction.UserAdminService;
import fiados.com.service.abstraction.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Api(value = "Controller method users", description = "Controller for interaction with users")
public class UserController {
    
    private UserAdminService userService;
    @ApiOperation(value = "Method to search for a list of users", notes = "Return list users")
    @GetMapping("/all")
    public List<ListUserResponse> getAll() {
        return userService.getAllUser();
    }

    
}

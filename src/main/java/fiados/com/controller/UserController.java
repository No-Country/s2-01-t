
package fiados.com.controller;
import fiados.com.models.response.UserFilterResponse;
import fiados.com.service.abstraction.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/user")
@Api(value = "Controller method users", description = "Controller for interaction with users")
public class UserController {

    @Autowired
    private AuthService userService;
    @ApiOperation(value = "Search users with filters", notes = "Returns users" )
    @GetMapping("/search")
    public ResponseEntity<List<UserFilterResponse>> searchUsers(@RequestParam(value = "term")String term){
        List<UserFilterResponse> responses = userService.searchUsers(term);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    

}

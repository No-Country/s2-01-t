package fiados.com.controller;


import fiados.com.models.response.UserFilterResponse;
import fiados.com.service.abstraction.AuthService;
import fiados.com.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/filter")
public class UserController {

    @Autowired
    private AuthService userService;

    @GetMapping("/search")
    public ResponseEntity<List<UserFilterResponse>> searchUsers(@RequestParam(value = "term")String term){
        List<UserFilterResponse> responses = userService.searchUsers(term);
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

}

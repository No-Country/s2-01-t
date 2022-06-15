package fiados.com.controller;

import fiados.com.models.entity.Trade;
import fiados.com.models.entity.User;
import fiados.com.models.request.AuthRequest;
import fiados.com.models.request.UserRegister;
import fiados.com.models.response.AuthResponse;
import fiados.com.models.response.UserResponse;
import fiados.com.service.abstraction.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
@Api(value = "Autenticate Controller", description = "Authentication handler login/register")
public class AuthController {

    @Autowired
    private AuthService authService;
    @ApiOperation(value = "Registration method", notes = "Returns a registered user" )
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRegister request){
        UserResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @ApiOperation(value = "User login to the system", notes = "Returns a login user" )
    @PostMapping("/login")
    private ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request){
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
    @ApiOperation(value = "Method that returns all the data of the user logged in the system", notes = "Returns a info user" )
    @GetMapping("/me")
    public ResponseEntity<User> getInfoUser(){
        return new ResponseEntity<>(authService.getInfoUser(), HttpStatus.OK);
    }
}

package fiados.com.service;

import fiados.com.auth.service.JwtUtil;
import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Role;
import fiados.com.models.entity.Trade;
import fiados.com.models.entity.User;
import fiados.com.models.enums.EnumRoles;
import fiados.com.models.mapper.UserMapper;
import fiados.com.models.request.AuthRequest;
import fiados.com.models.request.UserRegister;
import fiados.com.models.response.AuthResponse;
import fiados.com.models.response.UserResponse;
import fiados.com.repository.CustomerRepository;
import fiados.com.repository.TradeRepository;
import fiados.com.repository.UserRepository;
import fiados.com.service.abstraction.AuthService;
import fiados.com.service.abstraction.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, AuthService {

    private static final String USER_NOT_FOUND_MESSAGE = "User not found.";
    private static final String USER_EMAIL_ERROR = "Email address is already used.";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JwtUtil jwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserResponse register(UserRegister request) {
        if(userRepository.findByEmail(request.getEmail()) != null){
            throw new RuntimeException(USER_EMAIL_ERROR);
        }
       if(request.getRole().equalsIgnoreCase("client")) {
           Customer customer = userMapper.entityToDTO(request);
           customer.setPassword(passwordEncoder.encode(request.getPassword()));
           List<Role> roles = new ArrayList<>();
           roles.add(roleService.findBy(EnumRoles.CUSTOMER.getFullRoleName()));
           customer.setRoles(roles);
           Customer customerCreate = customerRepository.save(customer);
           UserResponse response = userMapper.dtoToEntity(customerCreate);
           response.setToken(jwt.generateTokenCustomer(customerCreate));
           return response;
       }

       if(request.getRole().equalsIgnoreCase("shop")){
           Trade trade = userMapper.entityTradeToDTO(request);
           trade.setPassword(passwordEncoder.encode(request.getPassword()));
           List<Role> roles = new ArrayList<>();
           roles.add(roleService.findBy(EnumRoles.MERCHANT.getFullRoleName()));
           trade.setRoles(roles);
           Trade tradeCreate = tradeRepository.save(trade);
           UserResponse response = userMapper.dtoToEntityTrade(tradeCreate);
           response.setToken(jwt.generateTokenTrade(tradeCreate));
           return response;
       }
       else {
           throw new RuntimeException("The role does not exist.");
       }
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = getUser(request.getEmail());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        return new AuthResponse(jwt.generateToken(user), user.getEmail(), user.getRole());
    }

    private User getUser(String username) {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE);
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUser(username);
    }
}

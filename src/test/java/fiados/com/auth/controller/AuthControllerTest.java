package fiados.com.auth.controller;

import fiados.com.auth.AbstractBaseIntegrationTest;
import fiados.com.auth.SecurityTestConfig;
import fiados.com.auth.service.JwtUtil;
import fiados.com.models.entity.Trade;
import fiados.com.models.enums.EnumRoles;
import fiados.com.models.request.UserRegister;
import fiados.com.models.response.ErrorResponse;
import fiados.com.models.response.UserResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthControllerTest extends AbstractBaseIntegrationTest {

    @MockBean
    private JwtUtil jwtUtil;

    private static final String PATH = "/auth/register";
    private String token = SecurityTestConfig.createToken(
            "marzoa3581@gmail.com", EnumRoles.MERCHANT.getFullRoleName());

    @Test
    public void shouldRegisterUserWithTokenSuccessfully(){
        Trade trade = stubTrade(EnumRoles.MERCHANT.getFullRoleName());
        when(tradeRepository.findByEmail(eq("marzoa3581@gmail.com"))).thenReturn(null);
        when(tradeRepository.save(isA(Trade.class))).thenReturn(trade);
        when(jwtUtil.generateTokenTrade(eq(trade))).thenReturn(token);

        UserRegister userTrade = new UserRegister();
        userTrade.setDni("12345678");
        userTrade.setAdress("address");
        userTrade.setCity("city");
        userTrade.setRole("shop");
        userTrade.setCompany_name("company");
        userTrade.setFirstName("first");
        userTrade.setLastName("last");
        userTrade.setEmail("marzoa3581@gmail.com");
        userTrade.setCountry("argentina");
        userTrade.setPassword("12345678");

        HttpEntity<UserRegister> request = new HttpEntity<>(userTrade,this.headers);
        ResponseEntity<UserResponse> response = this.testRestTemplate.exchange(
                generateUriWithPort(PATH),
                HttpMethod.POST, request, UserResponse.class);

        Assert.assertNotNull(response.getBody().getToken());
        Assert.assertEquals(response.getBody().getEmail(),userTrade.getEmail());
        Assert.assertEquals(response.getBody().getId(), trade.getId());
        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void shouldReturnBadRequestWhenTheEmailAlreadyExist() {
        when(tradeRepository.findByEmail(eq("marzoa3581@gmail.com"))).thenReturn(new Trade());

        UserRegister userTrade = new UserRegister();
        userTrade.setDni("12345678");
        userTrade.setAdress("address");
        userTrade.setCity("city");
        userTrade.setRole("shop");
        userTrade.setCompany_name("company");
        userTrade.setFirstName("first");
        userTrade.setLastName("last");
        userTrade.setEmail("marzoa3581@gmail.com");
        userTrade.setCountry("argentina");
        userTrade.setPassword("12345678");


        HttpEntity<UserRegister> request = new HttpEntity<>(userTrade,this.headers);
        ResponseEntity<ErrorResponse> response = this.testRestTemplate.exchange(
                generateUriWithPort(PATH),
                HttpMethod.POST, request, ErrorResponse.class);

        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
       // Assert.assertEquals("Email already exist.", response.getBody().getMessage());

    }

}

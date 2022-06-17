package fiados.com.auth;

import fiados.com.models.entity.Customer;
import fiados.com.models.entity.Role;
import fiados.com.models.entity.Trade;
import fiados.com.repository.CustomerRepository;
import fiados.com.repository.TradeRepository;
import fiados.com.service.abstraction.RoleService;
import org.assertj.core.util.Lists;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;



public class AbstractBaseIntegrationTest {

    @MockBean
    protected TradeRepository tradeRepository;
    @MockBean
    protected CustomerRepository customerRepository;
    @MockBean
    protected AuthenticationManager authenticationManager;
    protected org.springframework.http.HttpHeaders headers = new HttpHeaders();

    protected TestRestTemplate testRestTemplate = new TestRestTemplate();

    @MockBean
    protected RoleService roleService;
    @LocalServerPort
    protected int port;

    protected static final Long TRADE_ID = 1L ;

    protected String generateUriWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    protected void setAuthorizationHeaderBasedOn(String role) {
        String jwt = SecurityTestConfig.createToken("marzoa3581@gmail.com", role);
        headers.set("Authorization", jwt);
    }
    protected Role stubRole(String name){
        Role rol = new Role();
        rol.setId(TRADE_ID);
        rol.setName(name);
        return rol;
    }

    protected Trade stubTrade(String name){
        Trade trade = new Trade();
        trade.setId(TRADE_ID);
        trade.setDni("12345678");
        trade.setAdress("address");
        trade.setCity("city");
        trade.setRole("shop");
        trade.setCompany_name("company");
        trade.setFirstName("first");
        trade.setLastName("last");
        trade.setEmail("marzoa3581@gmail.com");
        trade.setSoftDelete(false);
        trade.setCountry("argentina");
        trade.setPassword("12345678");
        trade.setRoles(Lists.list(stubRole(name)));
        return trade;

    }
    protected Customer stubCustomer(String name){
        Customer customer = new Customer();
        customer.setId(TRADE_ID);
        customer.setDni("12345678");
        customer.setAdress("address");
        customer.setCity("city");
        customer.setRole("client");
        customer.setFirstName("first");
        customer.setLastName("last");
        customer.setEmail("marzoa3581@gmail.com");
        customer.setSoftDelete(false);
        customer.setCountry("argentina");
        customer.setPassword("12345678");
        customer.setRoles(Lists.list(stubRole(name)));
        return customer;
    }


}

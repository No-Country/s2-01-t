package fiados.com.auth.config;


import fiados.com.auth.filter.JwtRequestFilters;
import fiados.com.models.enums.EnumRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilters jwtRequestFilters;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private static final String[] publicEndpoint = {
            "/swagger-resources/**",
            "/swagger-ui/**", "/v2/api-docs",
            "/v3/api-docs",
            "/api/docs",
            "/api/docs/**",
            "/api/docs/swagger-ui",
            "/swagger-ui.html",
            "/**/swagger-ui/**",
            "/swagger-ui"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth/register").permitAll()
                .antMatchers(HttpMethod.POST,"/auth/login").permitAll()
                .antMatchers(HttpMethod.GET,"/user/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/user/{id}").permitAll() //hasAnyAuthority("ROLE_ADMIN")
                .antMatchers(HttpMethod.GET,"/user/me").permitAll()
                .antMatchers(HttpMethod.GET,"/api/vi/category").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/category/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/category").permitAll()
                .antMatchers(HttpMethod.DELETE,"/api/v1/category/{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/v1/category/{id}").permitAll()
                    //customer
                .antMatchers(HttpMethod.GET,"/api/v1/customer/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/customer/all").permitAll()                
                .antMatchers(HttpMethod.DELETE,"/api/v1/customer/{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/v1/customer/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/api/v1/customer/customer_comment").permitAll()
                    //trade
                .antMatchers(HttpMethod.DELETE,"/api/v1/trade/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/trade/me").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/trade/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/trade/all").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/v1/trade/{id}").permitAll()
                    //Branch
                .antMatchers(HttpMethod.POST,"/api/v1/branch").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/branch/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/api/v1/branch/all").permitAll()
                .antMatchers(HttpMethod.DELETE,"/api/v1/branch/{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/v1/branch/{id}").permitAll()
                    //Point
                .antMatchers(HttpMethod.POST,"api/v1/point").hasAnyAuthority(EnumRoles.MERCHANT.getFullRoleName())
                .antMatchers(HttpMethod.POST,"api/v1/point/{id}").permitAll()

                .antMatchers(publicEndpoint).permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtRequestFilters, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }
}

package  fiados.com.auth.config;

import io.swagger.annotations.AuthorizationScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@EnableWebMvc
public class SwaggerConfig {


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();

    }
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("CallVideo", "No Country .", "1.0", "Terms of service",
                new springfox.documentation.service.Contact("No Country", "www.fiado.com", "noCountry@gmail.com"), "License of API", "API license URL",
                Collections.emptyList());
        return apiInfo;
    }


    private SecurityContext securityContext(){
        return SecurityContext.builder()
                .securityReferences(defaultAuth()).build();
    }

    private List<SecurityReference> defaultAuth(){
        springfox.documentation.service.AuthorizationScope authorizationScope
                = new springfox.documentation.service.AuthorizationScope("global",
                "accessEverything");
        springfox.documentation.service.AuthorizationScope[] authorizationScopes
                = new springfox.documentation.service.AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Bearer", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }

}

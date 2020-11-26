package com.miller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.
                        basePackage("com.miller.rest.controller")
                )
                .paths(PathSelectors.any())
                .build()
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiKey()))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return  new ApiInfoBuilder()
                .title("Seller")
                .description("Seller project api")
                .version("0.0.1")
                .contact(contact())
                .build();
    }

    private Contact contact() {
        return new Contact(
                "Miller",
                "https://github.com/miller00315",
                "miller00315@gmail.com");
    }

    public ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext
                .builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope =
                new AuthorizationScope("global", "accessEverything");

        AuthorizationScope[] scopes = new AuthorizationScope[1];

        scopes[0] = authorizationScope;

        SecurityReference reference = new SecurityReference("JWT", scopes);

        List<SecurityReference> auths = new ArrayList<>();

        auths.add(reference);

        return  auths;
    }
}

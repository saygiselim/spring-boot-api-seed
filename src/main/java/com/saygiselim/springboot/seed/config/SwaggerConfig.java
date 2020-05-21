package com.saygiselim.springboot.seed.config;

import com.saygiselim.springboot.seed.ApplicationProperties;
import com.saygiselim.springboot.seed.security.SecurityConstants;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
@Profile("!test")
public class SwaggerConfig {
    private static final String[] SECURITY_WHITELIST = {
            // Authentication
            SecurityConstants.SIGN_UP_URL,
            SecurityConstants.SIGN_IN_URL
    };

    private final ApplicationProperties applicationProperties;

    @Autowired
    public SwaggerConfig(ApplicationProperties applicationProperties) {this.applicationProperties = applicationProperties;}

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.ant("/**"))
                .build()
                .securitySchemes(Collections.singletonList(getSecurityScheme()))
                .securityContexts(Collections.singletonList(getSecurityContext()))
                .apiInfo(getApiInfo());
    }

    private SecurityScheme getSecurityScheme() {
        return new ApiKey("Bearer <JWT>", SecurityConstants.HEADER_STRING, "header");
    }

    private SecurityContext getSecurityContext() {
        AuthorizationScope[] authorizationScopes = {
                new AuthorizationScope("Read", "Read operations"),
                new AuthorizationScope("Write", "Write operations")
        };

        List<SecurityReference> refs = Collections.singletonList(new SecurityReference("Bearer <JWT>", authorizationScopes));

        return SecurityContext.builder()
                .securityReferences(refs)
                .forPaths(path -> path != null && !Arrays.asList(SECURITY_WHITELIST).contains(path))
                .build();
    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title(applicationProperties.getTitle())
                .description(applicationProperties.getDescription())
                .build();
    }
}
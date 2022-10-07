package com.bi.recordmanagement.config;

import com.fasterxml.classmate.TypeResolver;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private String REST_PACKAGE_PATH = "com.bi.recordmangement.controllers";
    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()                 
//                .apis(RequestHandlerSelectors.any())   
//               // .apis(RequestHandlerSelectors.basePackage("com.gm.clinic.controller"))
//                .paths(PathSelectors.any())  
//            	.paths(Predicates.not(PathSelectors.regex("/error.*")))
//                .build()
//        		.apiInfo(metaData());
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(REST_PACKAGE_PATH))
//                .apis(Predicates.and(RequestHandlerSelectors.basePackage(REST_PACKAGE_PATH), RequestHandlerSelectors.basePackage("org.springframework.security.oauth2.client")))
//                .apis(RequestHandlerSelectors.basePackage("org.springframework.security.oauth2.client"))
                .paths(PathSelectors.any())
                .build().pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(newRule(typeResolver.resolve(DeferredResult.class, typeResolver.resolve(ResponseEntity.class, WildcardType.class)), typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(false)
                .apiInfo(getApiInfo())
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()));

    }

    private ApiInfo getApiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot REST API",
                "Spring Boot REST API for clinics",
                "1.0",
                "Terms of service",
                new Contact("GoMedii Auth Service", "https://example.com/about/", "info@gomedii.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
        return apiInfo;
    }

    private ApiKey apiKey() {
        return new ApiKey("mykey", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/*.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = {authorizationScope};
        return Collections.singletonList(new SecurityReference("mykey", authorizationScopes));
    }

    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(null, null, null, null, "Bearer access_token", ApiKeyVehicle.HEADER, "Authorization", ",");
//	    return SecurityConfigurationBuilder.builder()
//	        .clientId(null)
//	        .clientSecret(null)
//	        .realm(null)
//	        .appName(null)
//	        .scopeSeparator(",")
//	        .additionalQueryStringParams(null)
//	        .useBasicAuthenticationWithAccessCodeGrant(true)
//	        .build();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}


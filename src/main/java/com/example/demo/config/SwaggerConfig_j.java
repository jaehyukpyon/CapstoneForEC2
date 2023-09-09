package com.example.demo.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig_j {

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.OAS_30)
//                .useDefaultResponseMessages(false)
//                .select()
//                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
//                .paths(PathSelectors.any())
//                .build()
//                .securitySchemes(Arrays.asList(apiKey()))
//                .securityContexts(Arrays.asList(securityContext()))
//                .apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("상명대학교 2023 캡스톤 - 애완동물특공대 API")
//                .build();
//    }
//
//    private ApiKey apiKey() {
//        return new ApiKey("Authorization", "Bearer Token", "header");
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder().securityReferences(defaultAuth())
//                .operationSelector(selector -> selector.requestMappingPattern().startsWith("/api/")).build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//        AuthorizationScope authorizationScope = new AuthorizationScope("global", "global access");
//        return Arrays.asList(new SecurityReference("Authorization", new AuthorizationScope[] {authorizationScope}));
//    }
}

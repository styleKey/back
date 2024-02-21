package com.thekey.stylekeyserver.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenApiCustomizer securityOpenApiCustomizer() {
        String securityRequirementName = "Bearer을 제외하고 accessToken을 입력해주세요.";
        return openApi -> openApi
            .components(new Components()
                .addSecuritySchemes(securityRequirementName,
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")))
            .addSecurityItem(new SecurityRequirement()
                .addList(securityRequirementName));
    }

    @Bean
    public OpenApiCustomizer customOpenApiCustomizer() {
        return openApi -> openApi.info(apiInfo("StyleKEY API"));
    }

    @Bean
    public OpenApiCustomizer adminOpenApiCustomizer() {
        return openApi -> openApi.info(apiInfo("관리자페이지 API"));
    }

    @Bean
    public OpenApiCustomizer userOpenApiCustomizer() {
        return openApi -> openApi.info(apiInfo("서비스페이지 API"));
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
            .group("ADMIN")
            .pathsToMatch("/admin/**")
            .addOpenApiCustomizer(customOpenApiCustomizer())
            .addOpenApiCustomizer(adminOpenApiCustomizer())
            .addOpenApiCustomizer(securityOpenApiCustomizer())
            .build();
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
            .group("SERVICE")
            .pathsToMatch("/api/**")
            .addOpenApiCustomizer(customOpenApiCustomizer())
            .addOpenApiCustomizer(userOpenApiCustomizer())
            .addOpenApiCustomizer(securityOpenApiCustomizer())
            .build();
    }

    private Info apiInfo(String title) {
        return new Info()
            .title(title)
            .description("패션 취향 테스트를 통한 사용자 맞춤형 패션 정보 제공 서비스")
            .version("1.0.0");
    }
}

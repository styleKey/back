package com.thekey.stylekeyserver.swagger;

import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

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
                .build();
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("SERVICE")
                .pathsToMatch("/api/**")
                .addOpenApiCustomizer(customOpenApiCustomizer())
                .addOpenApiCustomizer(userOpenApiCustomizer())
                .build();
    }

    private Info apiInfo(String title) {
        return new Info()
                .title(title)
                .description("패션 취향 테스트를 통한 사용자 맞춤형 패션 정보 제공 서비스")
                .version("1.0.0");
    }
}

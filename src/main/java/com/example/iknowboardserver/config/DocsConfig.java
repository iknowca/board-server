package com.example.iknowboardserver.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition
@RequiredArgsConstructor
public class DocsConfig {

    @Bean
    public OpenAPI openAPI() {
        OpenAPI openAPI = new OpenAPI();
        openAPI.info(new Info().title("Board Server").description("Board 도메인을 담당하는 서버입니다.").version("1.0"));

        openAPI.setComponents(new Components());
        return openAPI;
    }
}


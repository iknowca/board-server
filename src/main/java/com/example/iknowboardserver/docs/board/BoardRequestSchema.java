package com.example.iknowboardserver.docs.board;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class BoardRequestSchema {
    private final OpenAPI openAPI;
    @Bean
    public Schema createBoardRequest() {
        Schema boardContentSchema = new Schema<Map<String, Object>>()
                .type("object")
                .addProperty("content", new StringSchema().example("text content"));
        Schema request = new Schema<Map<String, Object>>()
                .type("object")
                .contentMediaType("application/json")
                .name("create board request")
                .addProperty("title", new StringSchema().example("title"))
                .addProperty("boardContent", boardContentSchema);

        openAPI.getComponents().addSchemas("CreateBoardRequest", request);
        return request;
    }
}

package com.example.iknowboardserver.docs.board;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.ArraySchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.media.StringSchema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Getter
@RequiredArgsConstructor
public class BoardResponseSchema {
    private final OpenAPI openAPI;

    @Bean
    public Schema createBoardResponse() {
        Schema response = new Schema<Map<String, Object>>()
                .type("object")
                .contentMediaType("application/json")
                .name("create board response")
                .addProperty("id", new StringSchema().example("1"))
                .addProperty("createdAt", new StringSchema().example("2024-03-11T13:50:06.032Z"))
                .addProperty("updatedAt", new StringSchema().example("2024-03-11T13:50:06.034Z"))
                .addProperty("title", new StringSchema().example("title"))
                .addProperty("writerId", new StringSchema().example("1"));

        openAPI.getComponents().addSchemas("CreateBoardResponse", response);
        return response;
    }

    @Bean
    public Schema getBoardResponse() {
        Schema boardContent = new Schema<Map<String, Object>>()
                .addProperty("id", new StringSchema().example("1"))
                .addProperty("content", new StringSchema().example("text content"));
        Schema response = new Schema<Map<String, Object>>()
                .type("object")
                .contentMediaType("application/json")
                .name("get board response")
                .addProperty("id", new StringSchema().example("1"))
                .addProperty("createdAt", new StringSchema().example("2024-03-11T13:50:06.032Z"))
                .addProperty("updatedAt", new StringSchema().example("2024-03-11T13:50:06.034Z"))
                .addProperty("title", new StringSchema().example("title"))
                .addProperty("writerId", new StringSchema().example("1"))
                .addProperty("boardContent", boardContent);

        openAPI.getComponents().addSchemas("GetBoardResponse", response);
        return response;
    }

    @Bean
    public Schema INVALID_BOARD_RESPONSE() {
        Schema response = new Schema<Map<String, Object>>()
                .type("object")
                .contentMediaType("application/json")
                .name("invalid board response")
                .addProperty("message", new StringSchema().example("INVALID_BOARD"));
        openAPI.getComponents().addSchemas("INVALID_BOARD_RESPONSE", response);
        return response;
    }

    @Bean
    public Schema getBoardListResponse() {
        Schema board = new Schema<Map<String, Object>>()
                .name("get board response")
                .addProperty("id", new StringSchema().example("1"))
                .addProperty("createdAt", new StringSchema().example("2024-03-11T13:50:06.032Z"))
                .addProperty("updatedAt", new StringSchema().example("2024-03-11T13:50:06.034Z"))
                .addProperty("title", new StringSchema().example("title"))
                .addProperty("writerId", new StringSchema().example("1"));
        Schema response = new Schema<Map<String, Object>>()
                .type("object")
                .contentMediaType("application/json")
                .name("get board list response")
                .addProperty("boards", new ArraySchema().items(board))
                .addProperty("status", new StringSchema().example("success"));
        openAPI.getComponents().addSchemas("GetBoardListResponse", response);
        return response;
    }
}

package com.example.iknowboardserver.domain.board.controller;

import com.example.iknowboardserver.SpringBootTestClass;
import com.example.iknowboardserver.domain.board.dto.BoardDTO;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("게시글 수정 테스트")
public class BoardPutTest extends SpringBootTestClass {
    @Nested
    @DisplayName("클라이언트가 게시글을 수정을 요청하면")
    class Describe_request_board_put {
        BoardDTO request;
        Long boardId;

        String title;
        String content;
        String newTitle;
        String newContent;

        @Nested
        @DisplayName("제목과 내용을 입력하면")
        class Context_with_title_and_content {
            @BeforeEach
            void setUp() throws Exception {
                title = RandomStringUtils.random(10, true, true);
                content = RandomStringUtils.random(200, true, true);
                newTitle = RandomStringUtils.random(10, true, true);
                newContent = RandomStringUtils.random(200, true, true);


                request = new BoardDTO();
                request.setTitle(title);
                request.setContent(content);
                System.out.println("request = " + request.getContent());
                mockMvc.perform(MockMvcRequestBuilders.post("/board")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request)))
                        .andExpect(status().isOk())
                        .andDo(result -> {
                            JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
                            boardId = jsonNode.get("data").get("id").asLong();
                        });

                request = new BoardDTO();
                request.setTitle(newTitle);
                request.setContent(newContent);

            }

            @Nested
            @DisplayName("게시글이 존재하면")
            class Context_with_board_exists {
                @Test
                @DisplayName("게시글이 수정된다")
                void It_updates_board() throws Exception {
                    mockMvc.perform(MockMvcRequestBuilders.put("/board/" + boardId, request)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(request)))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.data.id").value(boardId))
                            .andExpect(jsonPath("$.data.title").value(newTitle))
                            .andExpect(jsonPath("$.data.content").value(newContent));

                }
            }

            @Nested
            @DisplayName("게시글이 존재하지 않으면")
            class Context_with_no_board {
                @Test
                @DisplayName("404 에러를 반환한다")
                void it_returns_404Error() throws Exception {
                    mockMvc.perform(MockMvcRequestBuilders.put("/board/0")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(request)))
                            .andExpect(status().isNotFound());
                }
            }
        }

    }
}

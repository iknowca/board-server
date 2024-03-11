package com.example.iknowboardserver.domain.board.controller;

import com.example.iknowboardserver.SpringBootTestClass;
import com.example.iknowboardserver.domain.board.dto.BoardContentDTO;
import com.example.iknowboardserver.domain.board.dto.BoardDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.JsonPath;
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
        Long id;

        String title;
        String content;
        String newTitle;
        String newContent;

        @Nested
        @DisplayName("제목과 내용을 입력하면")
        class Context_with_title_and_content {
            @BeforeEach
            void setUp() throws Exception {
                String title = RandomStringUtils.random(10, true, false);
                String content = RandomStringUtils.random(200, true, false);
                BoardContentDTO requestBoardContent = new BoardContentDTO();
                requestBoardContent.setContent(content);
                BoardDTO postRequest = new BoardDTO();
                postRequest.setTitle(title);
                postRequest.setBoardContent(requestBoardContent);

                mockMvc.perform(MockMvcRequestBuilders.post("/board")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(postRequest)))
                        .andExpect(status().isOk())
                        .andDo(result -> {
                            id = JsonPath.parse(result.getResponse().getContentAsString()).read("$.data.id", Long.class);
                        });
            }

            @Nested
            @DisplayName("게시글이 존재하면")
            class Context_with_board_exists {
                @BeforeEach
                void setUp() {
                    newTitle = RandomStringUtils.random(10, true, false);
                    newContent = RandomStringUtils.random(200, true, false);
                    BoardContentDTO requestBoardContent = new BoardContentDTO();
                    requestBoardContent.setContent(newContent);
                    request = new BoardDTO();
                    request.setId(id);
                    request.setTitle(newTitle);
                    request.setBoardContent(requestBoardContent);
                }

                @Test
                @DisplayName("게시글이 수정된다")
                void It_updates_board() throws Exception {
                    mockMvc.perform(MockMvcRequestBuilders.put("/board/" + id)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(request)))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.data.id").value(id))
                            .andExpect(jsonPath("$.data.title").value(newTitle));
                }
            }

            @Nested
            @DisplayName("게시글이 존재하지 않으면")
            class Context_with_no_board {
                @Test
                @DisplayName("400 에러를 반환한다")
                void it_returns_400Error() throws Exception {
                    mockMvc.perform(MockMvcRequestBuilders.put("/board/0")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(objectMapper.writeValueAsString(request)))
                            .andExpect(status().isBadRequest());
                }
            }
        }

    }
}

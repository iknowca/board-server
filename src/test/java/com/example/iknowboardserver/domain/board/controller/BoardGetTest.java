package com.example.iknowboardserver.domain.board.controller;

import com.example.iknowboardserver.SpringBootTestClass;
import com.example.iknowboardserver.domain.board.dto.BoardContentDTO;
import com.example.iknowboardserver.domain.board.dto.BoardDTO;
import com.example.iknowboardserver.domain.board.entity.Board;
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

@DisplayName("게시글 조회 테스트")
public class BoardGetTest extends SpringBootTestClass {
    @Nested
    @DisplayName("클라이언트가 게시글을 조회를 요청하면")
    class Describe_request_board_get {
        Long id;
        String title;
        String content;
        Board board;

        @BeforeEach
        void setUp() throws Exception {
            title = RandomStringUtils.random(10, true, false);
            content = RandomStringUtils.random(200, true, false);
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
            @Test
            @DisplayName("게시글이 반환된다")
            void it_returns_board() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/board/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data.id").exists())
                        .andExpect(jsonPath("$.data.title").value(title))
                        .andExpect(jsonPath("$.data.boardContent.content").value(content));
            }
        }

        @Nested
        @DisplayName("게시글이 존재하지 않으면")
        class Context_with_no_board {
            Long id = 0L;

            @Test
            @DisplayName("404 에러를 반환한다")
            void it_returns_404Error() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/board/" + id))
                        .andExpect(status().isNotFound());
            }
        }
    }
}

package com.example.iknowboardserver.domain.board.controller;

import com.example.iknowboardserver.SpringBootTestClass;
import com.example.iknowboardserver.domain.board.dto.BoardContentDTO;
import com.example.iknowboardserver.domain.board.dto.BoardDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("게시글 목록 조회 테스트")
public class BoardListGetTest extends SpringBootTestClass {
    @DisplayName("클라이언트가 게시글 목록을 조회를 요청하면")
    @Nested
    class Describe_request_board_list_get {
        @DisplayName("게시글이 존재하면")
        @Nested
        class Context_with_board_exists {
            @BeforeEach
            void setUp() throws Exception {
                for(int i=0; i<5; i++) {
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
                            .andExpect(status().isOk());
                }
            }
            @DisplayName("게시글 목록이 반환된다")
            @Test
            void it_returns_board_list() throws Exception {
                mockMvc.perform(MockMvcRequestBuilders.get("/board/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        )
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.data").isArray());
            }
        }
    }

}

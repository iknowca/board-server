package com.example.iknowboardserver.domain.board.service;

import com.example.iknowboardserver.SpringBootTestClass;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("게시판 조회 UNIT 테스트")
public class GetBoardTest extends SpringBootTestClass {
    Long id;
    @Nested
    @DisplayName("게시판 ID가 주어진다면")
    class Describe_boardId {
        @Nested
        @DisplayName("게시판을 조회한다")
        class Context_getBoard {

            @Test
            @DisplayName("게시판이 조회된다")
            void it_gets_board() {

            }
        }

        @Nested
        @DisplayName("게시판이 존재하지 않는다면")
        class Context_boardNotFound {
            @Test
            @DisplayName("게시판이 조회되지 않는다")
            void it_does_not_get_board() {

            }
        }
    }
    @Nested
    @DisplayName("게시판 ID가 주어지지 않는다면")
    class Describe_boardIdNotGiven {
        @Test
        @DisplayName("게시판이 조회되지 않는다")
        void it_does_not_get_board() {

        }
    }
}

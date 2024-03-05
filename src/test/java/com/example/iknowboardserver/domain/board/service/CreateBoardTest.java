package com.example.iknowboardserver.domain.board.service;

import com.example.iknowboardserver.SpringBootTestClass;
import com.example.iknowboardserver.domain.board.dto.BoardDTO;
import com.example.iknowboardserver.domain.board.entity.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게시판 생성 UNIT 테스트")
public class CreateBoardTest extends SpringBootTestClass {
    @Nested
    @DisplayName("requestForm이 주어진다면")
    class Describe_requestForm {
        BoardDTO request;

        @Nested
        @DisplayName("게시판을 생성한다")
        class Context_createBoard {
            @BeforeEach
            void setUp() {
                request = new BoardDTO();
                request.setTitle("제목");
                request.setContent("내용");
            }
            @Test
            @DisplayName("게시판이 생성된다")
            void it_creates_board() {
                Board board = boardService.createBoard(request);

                assertThat(board.getTitle()).isEqualTo(request.getTitle());
                assertThat(board.getContent()).isEqualTo(request.getContent());

                Optional<Board> maybeBoard = boardMapper.selectById(board.getId());
                assertThat(maybeBoard).isPresent();
                Board boardInDB = maybeBoard.get();
                assertThat(boardInDB.getTitle()).isEqualTo(request.getTitle());
                assertThat(boardInDB.getContent()).isEqualTo(request.getContent());
            }
        }
    }
}

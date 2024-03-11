package com.example.iknowboardserver.domain.board.controller;

import com.example.iknowboardserver.domain.board.dto.BoardContentDTO;
import com.example.iknowboardserver.domain.board.dto.BoardDTO;
import com.example.iknowboardserver.domain.board.entity.Board;
import com.example.iknowboardserver.domain.board.entity.BoardContent;
import com.example.iknowboardserver.domain.board.service.BoardService;
import com.example.iknowboardserver.util.responseBody.DTOResponseBody;
import com.example.iknowboardserver.util.responseBody.MessageResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<DTOResponseBody<BoardDTO>> createBoard(@RequestBody BoardDTO request) {
        Board board = boardService.createBoard(request);

        return ResponseEntity.ok(new DTOResponseBody<>(BoardDTO.from(board), "success"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DTOResponseBody<BoardDTO>> getBoard(@PathVariable Long id) {
        Board board = boardService.getBoard(id);
        BoardContent boardContent = boardService.getBoardContent(board.getContentId());
        BoardDTO response = BoardDTO.from(board);
        response.setBoardContent(BoardContentDTO.from(boardContent));
        return ResponseEntity.ok(new DTOResponseBody<>(response, "success"));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DTOResponseBody<BoardDTO>> updateBoard(@PathVariable Long id, @RequestBody BoardDTO request) {
        Board board = boardService.updateBoard(id, request);
        return ResponseEntity.ok(new DTOResponseBody<>(BoardDTO.from(board), "success"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponseBody> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok(new MessageResponseBody("게시글이 삭제되었습니다.", "success"));
    }
    @GetMapping("/list")
    public ResponseEntity<DTOResponseBody<List<BoardDTO>>> getBoardList() {
        List<Board> boardList = boardService.getBoardList();
        return ResponseEntity.ok(new DTOResponseBody<>(BoardDTO.from(boardList), "success"));
    }
}

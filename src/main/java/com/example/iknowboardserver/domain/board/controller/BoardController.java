package com.example.iknowboardserver.domain.board.controller;

import com.example.iknowboardserver.domain.board.controller.DTO.BoardDTO;
import com.example.iknowboardserver.domain.board.entity.Board;
import com.example.iknowboardserver.domain.board.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<Result<BoardDTO>> createBoard(@RequestBody BoardDTO request) {
        Board board = boardService.createBoard(request);

        return ResponseEntity.ok(new Result<>(BoardDTO.from(board), "success"));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Result<BoardDTO>> getBoard(@PathVariable Long id) {
        Board board = boardService.getBoard(id);
        return ResponseEntity.ok(new Result<>(BoardDTO.from(board), "success"));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Result<BoardDTO>> updateBoard(@PathVariable Long id, @RequestBody BoardDTO request) {
        Board board = boardService.updateBoard(id, request);
        return ResponseEntity.ok(new Result<>(BoardDTO.from(board), "success"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok(new Result(null, "success"));
    }
    @Getter
    public static class Result<T> {
        private final T data;
        private final String message;

        public Result(T data, String message) {
            this.data = data;
            this.message = message;
        }
    }
}

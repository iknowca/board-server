package com.example.iknowboardserver.domain.board.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BoardExceptionHandler {
    @ExceptionHandler(BoardException.class)
    public ResponseEntity<String> handler(BoardException e) {
        return ResponseEntity.status(e.error.getStatus()).body("message: " + e.getMessage());
    }
}

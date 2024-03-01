package com.example.iknowboardserver.domain.board.exception;

import lombok.Getter;

public class BoardException extends RuntimeException {
    BOARD_ERROR error;

    public enum BOARD_ERROR {
        INVALID_BOARD(404, "유효하지 않은 게시판입니다.");
        @Getter
        private final int status;
        @Getter
        private final String message;

        BOARD_ERROR(int status, String s) {
            this.status = status;
            this.message = s;
        }
    }

    public BoardException(BOARD_ERROR error) {
        super(error.name());
        this.error = error;
    }


}

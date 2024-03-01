package com.example.iknowboardserver.domain.board.service;

import com.example.iknowboardserver.domain.board.controller.DTO.BoardDTO;
import com.example.iknowboardserver.domain.board.entity.Board;

public interface BoardService {
    Board createBoard(BoardDTO reqForm);

    Board getBoard(Long id);

    Board updateBoard(Long id, BoardDTO reqForm);

    void deleteBoard(Long id);
}

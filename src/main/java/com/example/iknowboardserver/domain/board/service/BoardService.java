package com.example.iknowboardserver.domain.board.service;

import com.example.iknowboardserver.domain.board.dto.BoardContentDTO;
import com.example.iknowboardserver.domain.board.dto.BoardDTO;
import com.example.iknowboardserver.domain.board.entity.Board;
import com.example.iknowboardserver.domain.board.entity.BoardContent;

import java.util.List;

public interface BoardService {
    Board createBoard(BoardDTO reqForm);

    Board getBoard(Long id);

    Board updateBoard(Long id, BoardDTO reqForm);

    void deleteBoard(Long id);

    List<Board> getBoardList();

    BoardContent getBoardContent(Long contentId);
}

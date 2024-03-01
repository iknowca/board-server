package com.example.iknowboardserver.domain.board.service;

import com.example.iknowboardserver.domain.board.controller.DTO.BoardDTO;
import com.example.iknowboardserver.domain.board.entity.Board;
import com.example.iknowboardserver.domain.board.exception.BoardException;
import com.example.iknowboardserver.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    final BoardRepository boardRepository;

    @Override
    public Board createBoard(BoardDTO request) {
        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        board = boardRepository.save(board);
        return board;
    }

    @Override
    public Board getBoard(Long id) {
        Optional<Board> maybeBoard = boardRepository.findById(id);
        if (maybeBoard.isEmpty()) {
            throw new BoardException(BoardException.BOARD_ERROR.INVALID_BOARD);
        }
        Board board = maybeBoard.get();
        return board;
    }

    @Override
    public Board updateBoard(Long id, BoardDTO request) {
        Optional<Board> maybeBoard = boardRepository.findById(id);
        if (maybeBoard.isEmpty()) {
            throw new BoardException(BoardException.BOARD_ERROR.INVALID_BOARD);
        }
        Board board = maybeBoard.get();
        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        board = boardRepository.save(board);
        return board;
    }


    @Override
    public void deleteBoard(Long id) {
        Optional<Board> maybeBoard = boardRepository.findById(id);
        if (maybeBoard.isEmpty()) {
            throw new BoardException(BoardException.BOARD_ERROR.INVALID_BOARD);
        }
        boardRepository.deleteById(id);
    }
}

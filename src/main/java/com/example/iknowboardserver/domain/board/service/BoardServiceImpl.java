package com.example.iknowboardserver.domain.board.service;

import com.example.iknowboardserver.domain.board.dto.BoardDTO;
import com.example.iknowboardserver.domain.board.entity.Board;
import com.example.iknowboardserver.domain.board.exception.BoardException;
import com.example.iknowboardserver.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    @Autowired
    private final BoardMapper boardMapper;

    @Override
    public Board createBoard(BoardDTO request) {
        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        boardMapper.insert(board);
        return board;
    }

    @Override
    public Board getBoard(Long id) {
        Optional<Board> maybeBoard = boardMapper.selectById(id);
        if (maybeBoard.isEmpty()) {
            throw new BoardException(BoardException.BOARD_ERROR.INVALID_BOARD);
        }
        Board board = maybeBoard.get();
        return board;
    }

    @Override
    public Board updateBoard(Long id, BoardDTO request) {
        Optional<Board> maybeBoard = boardMapper.selectById(id);
        if (maybeBoard.isEmpty()) {
            throw new BoardException(BoardException.BOARD_ERROR.INVALID_BOARD);
        }
        Board board = maybeBoard.get();
        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        boardMapper.update(board);
        return board;
    }


    @Override
    public void deleteBoard(Long id) {
        Optional<Board> maybeBoard = boardMapper.selectById(id);
        if (maybeBoard.isEmpty()) {
            throw new BoardException(BoardException.BOARD_ERROR.INVALID_BOARD);
        }
        boardMapper.delete(id);
    }

    @Override
    public List<Board> getBoardList() {
        return boardMapper.selectAll();
    }
}

package com.example.iknowboardserver.domain.board.service;

import com.example.iknowboardserver.domain.board.dto.BoardContentDTO;
import com.example.iknowboardserver.domain.board.dto.BoardDTO;
import com.example.iknowboardserver.domain.board.entity.Board;
import com.example.iknowboardserver.domain.board.entity.BoardContent;
import com.example.iknowboardserver.domain.board.exception.BoardException;
import com.example.iknowboardserver.mapper.BoardContentMapper;
import com.example.iknowboardserver.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardMapper boardMapper;
    private final BoardContentMapper boardContentMapper;

    @Override
    public Board createBoard(BoardDTO request) {
        Board board = request.toEntity();
        BoardContent boardContent = request.getBoardContent().toEntity();

        boardContentMapper.insert(boardContent);
        board.setContentId(boardContent.getId());
        board.setCreatedAt(LocalDateTime.now());

        boardMapper.insert(board);

        return board;
    }

    @Override
    public Board getBoard(Long id) {
        Optional<Board> maybeBoard = boardMapper.selectById(id);
        if (maybeBoard.isEmpty()) {
            throw new BoardException(BoardException.BOARD_ERROR.INVALID_BOARD);
        }
        Optional<BoardContent> maybeBoardContent = boardContentMapper.selectById(id);
        if (maybeBoardContent.isEmpty()) {
            throw new BoardException(BoardException.BOARD_ERROR.INVALID_BOARD);
        }

        Board board = maybeBoard.get();
        ;
        return board;
    }
    public BoardContent getBoardContent(Long id) {
        Optional<BoardContent> maybeBoardContent = boardContentMapper.selectById(id);
        if (maybeBoardContent.isEmpty()) {
            throw new BoardException(BoardException.BOARD_ERROR.INVALID_BOARD);
        }
        BoardContent boardContent = maybeBoardContent.get();
        return boardContent;
    }

    @Override
    public Board updateBoard(Long id, BoardDTO request) {
        Optional<Board> maybeBoard = boardMapper.selectById(id);
        if (maybeBoard.isEmpty()) {
            throw new BoardException(BoardException.BOARD_ERROR.INVALID_BOARD);
        }

        Board board = request.toEntity();
        board.setUpdatedAt(LocalDateTime.now());

        BoardContent boardContent = request.getBoardContent().toEntity();

        boardMapper.update(board);
        boardContentMapper.update(boardContent);
        return board;
    }


    @Override
    public void deleteBoard(Long id) {
        Board savedBoard = boardMapper.selectById(id).orElseThrow(() -> new BoardException(BoardException.BOARD_ERROR.INVALID_BOARD));
        BoardContent savedBoardContent = boardContentMapper.selectById(savedBoard.getContentId()).orElseThrow(() -> new BoardException(BoardException.BOARD_ERROR.INVALID_BOARD));

        boardContentMapper.delete(savedBoardContent.getId());
    }

    @Override
    public List<Board> getBoardList() {
        return boardMapper.selectAll();
    }
}

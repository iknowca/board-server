package com.example.iknowboardserver.mapper;

import com.example.iknowboardserver.domain.board.entity.Board;
import com.example.iknowboardserver.domain.board.entity.BoardContent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardContentMapper {
    Optional<BoardContent> selectById(Long id);
    Long insert(BoardContent boardContent);
    void delete(Long id);
    List<BoardContent> selectAll();
    void update(BoardContent boardContent);
}

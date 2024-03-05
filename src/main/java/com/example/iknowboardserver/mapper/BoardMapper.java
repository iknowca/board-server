package com.example.iknowboardserver.mapper;

import com.example.iknowboardserver.domain.board.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    void createTable();
    Optional<Board> selectById(Long id);
    void insert(Board board);
    void delete(Long id);
    List<Board> selectAll();
    void update(Board board);
}
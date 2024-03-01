package com.example.iknowboardserver.domain.board.controller.DTO;

import com.example.iknowboardserver.domain.board.entity.Board;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Builder
@Getter
@Setter
@AllArgsConstructor
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;

    public static BoardDTO from(Board board) {
        return BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt().toString())
                .updatedAt(board.getUpdatedAt().toString())
                .build();
    }

    public static List<BoardDTO> from(List<Board> boardList) {
        return boardList.stream().map(BoardDTO::from).toList();
    }
}

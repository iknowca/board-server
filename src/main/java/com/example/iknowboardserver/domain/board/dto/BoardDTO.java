package com.example.iknowboardserver.domain.board.dto;

import com.example.iknowboardserver.domain.board.entity.Board;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;

    private Long writerId;

    public static BoardDTO from(Board board) {
        return BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .build();
    }
    public static List<BoardDTO> from(List<Board> boards) {
        return boards.stream().map(BoardDTO::from).toList();
    }
}

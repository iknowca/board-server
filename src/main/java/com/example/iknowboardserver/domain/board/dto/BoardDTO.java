package com.example.iknowboardserver.domain.board.dto;

import com.example.iknowboardserver.domain.board.entity.Board;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
    private Long id;
    private String title;
    private BoardContentDTO boardContent;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Long writerId;

    public static BoardDTO from(Board board) {
        return BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
    public static List<BoardDTO> from(List<Board> boards) {
        return boards.stream().map(BoardDTO::from).toList();
    }
    public Board toEntity() {
        return Board.builder()
                .id(this.getId())
                .title(this.getTitle())
                .createdAt(this.getCreatedAt())
                .updatedAt(this.getUpdatedAt())
                .build();
    }
}

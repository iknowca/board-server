package com.example.iknowboardserver.domain.board.dto;

import com.example.iknowboardserver.domain.board.entity.BoardContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardContentDTO {
    private Long id;
    private String content;
    public static BoardContentDTO from(BoardContent boardContent) {
        return BoardContentDTO.builder()
                .id(boardContent.getId())
                .content(boardContent.getContent())
                .build();
    }
    public BoardContent toEntity() {
        return BoardContent.builder()
                .id(this.getId())
                .content(this.getContent())
                .build();
    }
}

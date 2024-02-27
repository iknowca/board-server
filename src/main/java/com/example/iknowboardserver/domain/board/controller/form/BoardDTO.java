package com.example.iknowboardserver.domain.board.controller.form;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;
}

package com.example.iknowboardserver.domain.board.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Board {
    private Long id;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;

    private Long writerId;
}
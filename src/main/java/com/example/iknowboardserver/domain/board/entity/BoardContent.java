package com.example.iknowboardserver.domain.board.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class BoardContent {
    private Long id;
    private String content;
}

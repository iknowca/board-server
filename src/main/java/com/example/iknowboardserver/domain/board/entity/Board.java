package com.example.iknowboardserver.domain.board.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Board {
    private Long id;
    private String title;

    private Long contentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long writerId;
}
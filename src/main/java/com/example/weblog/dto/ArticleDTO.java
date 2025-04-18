package com.example.weblog.dto;

import lombok.Data;

import java.time.LocalDateTime;

// 文章 DTO
@Data
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime publishDate;
    private Long authorId;
}
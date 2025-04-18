package com.example.weblog.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

// 文章實體，映射 articles 表
@Entity
@Table(name = "articles")
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime publishDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}
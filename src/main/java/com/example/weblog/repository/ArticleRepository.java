package com.example.weblog.repository;

import com.example.weblog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// 文章資料庫操作介面
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitleContainingIgnoreCase(String title);
}
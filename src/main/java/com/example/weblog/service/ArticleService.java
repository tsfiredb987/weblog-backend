package com.example.weblog.service;

import com.example.weblog.dto.ArticleDTO;
import com.example.weblog.entity.Article;
import com.example.weblog.entity.User;
import com.example.weblog.repository.ArticleRepository;
import com.example.weblog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

// 文章服務
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    // 獲取所有文章
    public List<ArticleDTO> getAllArticles() {
        return articleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 獲取單篇文章
    public ArticleDTO getArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        return convertToDTO(article);
    }

    // 新增文章
    public ArticleDTO createArticle(ArticleDTO articleDTO, String username) {
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setPublishDate(LocalDateTime.now());
        article.setAuthor(author);
        article = articleRepository.save(article);
        return convertToDTO(article);
    }

    // 更新文章
    public ArticleDTO updateArticle(Long id, ArticleDTO articleDTO, String username) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setAuthor(author);
        article = articleRepository.save(article);
        return convertToDTO(article);
    }

    // 刪除文章
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    // 搜尋文章
    public List<ArticleDTO> searchArticlesByTitle(String title) {
        return articleRepository.findByTitleContainingIgnoreCase(title).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 將實體轉為 DTO
    private ArticleDTO convertToDTO(Article article) {
        ArticleDTO dto = new ArticleDTO();
        dto.setId(article.getId());
        dto.setTitle(article.getTitle());
        dto.setContent(article.getContent());
        dto.setPublishDate(article.getPublishDate());
        dto.setAuthorId(article.getAuthor().getId());
        return dto;
    }
}
package com.example.weblog.controller;

import com.example.weblog.dto.ArticleDTO;
import com.example.weblog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

// 文章控制器
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // 獲取所有文章
    @GetMapping
    public List<ArticleDTO> getAllArticles() {
        return articleService.getAllArticles();
    }

    // 獲取單篇文章
    @GetMapping("/{id}")
    public ArticleDTO getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

//    @GetMapping("/{id}")
//    public ArticleDTO getArticle(@PathVariable Long id) {
//        return articleService.getArticleById(id);
//    }

    // 新增文章
    @PostMapping
    public ResponseEntity<ArticleDTO> createArticle(@RequestBody ArticleDTO articleDTO,
                                                    Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            logger.warn("未授權的文章創建嘗試");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "請登入以創建文章");
        }
        String username = authentication.getName();
        logger.debug("用戶 {} 正在創建文章", username);
        ArticleDTO createdArticle = articleService.createArticle(articleDTO, username);
        return ResponseEntity.ok(createdArticle);
    }

    // 更新文章
    @PutMapping("/{id}")
    public ArticleDTO updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO,
                                    Authentication authentication) {
        return articleService.updateArticle(id, articleDTO, authentication.getName());
    }

    // 刪除文章
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }

    // 搜尋文章
    @GetMapping("/search")
    public List<ArticleDTO> searchArticles(@RequestParam String title) {
        return articleService.searchArticlesByTitle(title);
    }
}
package com.example.weblog.controller;

import com.example.weblog.dto.ArticleDTO;
import com.example.weblog.service.ArticleService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 文章控制器
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

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

    // 新增文章
    @PostMapping
    public ArticleDTO createArticle(@RequestBody ArticleDTO articleDTO, Authentication authentication) {
        return articleService.createArticle(articleDTO, authentication.getName());
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
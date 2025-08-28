package com.example.javaSelfLearming.controller;

import com.example.javaSelfLearming.dto.ArticleForm;
import com.example.javaSelfLearming.entity.Article;
import com.example.javaSelfLearming.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles")
    public List<Article> getAllArticles() {
        return articleService.index();
    }

    @GetMapping("/articles/{id}")
    public Article getArticle(@PathVariable Long id) {
        return articleService.show(id);
    }

    // Post

    @PostMapping("/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm form) {
        Article created = articleService.save(form);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/transaction-test")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> articleList = articleService.createArticles(dtos);
        return (articleList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(articleList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // Patch

    @PatchMapping("/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm form) {
        Article updated = articleService.update(id, form);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // Delete

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}

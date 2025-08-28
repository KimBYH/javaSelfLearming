package com.example.javaSelfLearming.controller;

import com.example.javaSelfLearming.dto.ArticleForm;
import com.example.javaSelfLearming.entity.Article;
import com.example.javaSelfLearming.repository.ArticleRepository;
import com.example.javaSelfLearming.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
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

    @PostMapping("/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm form) {
        Article created = articleService.save(form);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PatchMapping("/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm form) {
        Article updated = articleService.update(id, form);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id) {
        Article deleted = articleService.delete(id);

        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).body(deleted) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //서비스 계층 만들기 전
//    @Autowired
//    private ArticleRepository articleRepository;
//
//    // Get
//
//    @GetMapping("/articles")
//    public List<Article> index(){
//        return articleRepository.findAll();
//    }
//
//    @GetMapping("/articles/{id}")
//    public Article getArticle(@PathVariable Long id){
//        return articleRepository.findById(id).orElseThrow( ()-> null);
//    }
//
//    // Post
//    @PostMapping("/articles")
//    public Article create(@RequestBody ArticleForm form){
//        Article article = form.toEntity();
//        return articleRepository.save(article);
//    }
//
//    // Patch
//    @PatchMapping("/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm form){
//        Article article = form.toEntity();
//
//        log.info("id : {}, article : {}",id, article.toString());
//
//        Article target = articleRepository.findById(id).orElseThrow( ()-> null);
//
//
//
//        if(target == null || id != article.getId()) {
//            log.info("잘못된 요청 ! id : {}, article : {} ", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        target.patch(article);
//        Article updated = articleRepository.save(target);
//
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//    // Delete
//
//    @DeleteMapping("/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        Article target = articleRepository.findById(id).orElseThrow( ()-> null);
//
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//
//        articleRepository.delete(target);
//
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }


}

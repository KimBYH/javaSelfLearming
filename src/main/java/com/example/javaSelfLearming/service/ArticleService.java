package com.example.javaSelfLearming.service;

import com.example.javaSelfLearming.dto.ArticleForm;
import com.example.javaSelfLearming.entity.Article;
import com.example.javaSelfLearming.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index(){
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article save(ArticleForm form) {
        Article article = form.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm form) {
        Article article = form.toEntity();

        log.info("id : {}, article : {}", id, article);

        Article target = articleRepository.findById(id).orElse(null);

        if(target == null || id != article.getId()) {
            log.info("id : {}, article : {}]", id, article);
            return null;
        }

        target.patch(article);
        return articleRepository.save(target);
    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);

        if (target == null) {
            return null;
        }

        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
        List<Article> articles = dtos.stream().map(
                dto-> dto.toEntity()).collect(Collectors.toList());

        articles.stream().forEach(article -> articleRepository.save(article));

        articleRepository.findById(-1L).orElseThrow(()-> new IllegalArgumentException("결제 실패!"));

        return articles;
    }
}

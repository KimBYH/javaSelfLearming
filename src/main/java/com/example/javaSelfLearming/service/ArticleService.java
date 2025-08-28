package com.example.javaSelfLearming.service;

import com.example.javaSelfLearming.dto.ArticleForm;
import com.example.javaSelfLearming.entity.Article;
import com.example.javaSelfLearming.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index(){
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElseThrow(()->null);
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

        Article target = articleRepository.findById(id).orElseThrow(()->null);

        if(target == null || id != article.getId()) {
            log.info("id : {}, article : {}]", id, article);
            return null;
        }

        target.patch(article);
        return articleRepository.save(target);
    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElseThrow(()->null);

        if (target == null) {
            return null;
        }

        articleRepository.delete(target);
        return target;
    }
}

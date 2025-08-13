package com.example.javaSelfLearming.controller;

import com.example.javaSelfLearming.dto.ArticleForm;
import com.example.javaSelfLearming.entity.Article;
import com.example.javaSelfLearming.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;

@Slf4j
@Controller
@RequestMapping("/articles")
public class articleController {

    @Autowired // 스프링에서 해당 객체를 주입시켜줌 (의존성 주입 DI : Dependency Injection)
    private ArticleRepository articleRepository;

    @GetMapping("")
    public String index(Model model){
        ArrayList<Article> articlesEntityList = articleRepository.findAll();

        model.addAttribute("articlesList", articlesEntityList);
        return "articles/index";
    }

    @GetMapping("/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {

        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity);

        return "articles/show";
    }

    @PostMapping("/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());

        Article article = form.toEntity();
        log.info(article.toString());

        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "greetings";
    }
}
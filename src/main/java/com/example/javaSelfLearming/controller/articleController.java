package com.example.javaSelfLearming.controller;

import com.example.javaSelfLearming.dto.ArticleForm;
import com.example.javaSelfLearming.entity.Article;
import com.example.javaSelfLearming.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
public class articleController {

    @Autowired // 스프링에서 해당 객체를 주입시켜줌 (의존성 주입 DI : Dependency Injection)
    private ArticleRepository articleRepository;

    @GetMapping("/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/create")
    public String createArticle(ArticleForm form){
        System.out.println(form.toString());
        Article article = form.toEntity();
        System.out.println(article.toString());
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());
        return "greetings";
    }
}
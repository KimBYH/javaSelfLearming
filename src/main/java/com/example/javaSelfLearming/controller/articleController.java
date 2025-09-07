package com.example.javaSelfLearming.controller;

import com.example.javaSelfLearming.dto.ArticleForm;
import com.example.javaSelfLearming.dto.CommentDto;
import com.example.javaSelfLearming.entity.Article;
import com.example.javaSelfLearming.repository.ArticleRepository;
import com.example.javaSelfLearming.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/articles")
public class articleController {

    @Autowired // 스프링에서 해당 객체를 주입시켜줌 (의존성 주입 DI : Dependency Injection)
    private ArticleRepository articleRepository;

    @Autowired
    private CommentService commentService;

    //GetMapping

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
        List<CommentDto> commentsDtos = commentService.getComments(id);

        model.addAttribute("article", articleEntity);
        model.addAttribute("commentsDtos", commentsDtos);

        return "articles/show";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model){
        Article articleEntity = articleRepository.findById(id).orElseThrow( ()-> null);

        model.addAttribute("article", articleEntity);

        log.info(articleEntity.toString());

        return "articles/edit";
    }

    //Post Mapping

    // 게시글 생성
    @PostMapping("/create")
    public String createArticle(ArticleForm form){
        log.info(form.toString());

        Article article = form.toEntity();
        log.info(article.toString());

        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteArticle(@PathVariable Long id, RedirectAttributes rttr){

        log.info("삭제 요청이 들어왔습니다.");

        Article article = articleRepository.findById(id).orElseThrow( ()-> null);

        log.info(article.toString());

        if (article != null) {
            log.info("삭제 처리중 ...");
           articleRepository.delete(article);
           rttr.addFlashAttribute("msg", "삭제되었습니다.");
        }

        return "redirect:/articles";
    }

    //게시글 업데이트
    @PostMapping("/update")
    public String updateArticle(ArticleForm form){

        Article ArticleEntity = form.toEntity();

        Article target = articleRepository.findById(ArticleEntity.getId()).orElseThrow(()-> null);

        if(target != null){
            articleRepository.save(ArticleEntity);
        }

        return "redirect:/articles/" + ArticleEntity.getId();
    }
}
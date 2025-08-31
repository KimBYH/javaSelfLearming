package com.example.javaSelfLearming.service;

import com.example.javaSelfLearming.dto.ArticleForm;
import com.example.javaSelfLearming.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Transactional
    @Test
    void create_success(){
        //예상 데이터
        String title = "title입니다.";
        String content = "content입니다.";
        ArticleForm form = new ArticleForm(null, title, content);

        Article expected = new Article(4L,"title입니다.", "content입니다.");

        //실제 데이터
        Article article = articleService.save(form);

        //검증
        assertEquals(article.toString(),expected.toString());
    }

    @Test
    void create_실패_id가_포함된_dto_입력(){
        //예상 데이터
        Long id = 4L;
        String title = "title입니다.";
        String content = "content입니다.";
        ArticleForm form = new ArticleForm(id, title, content);

        Article expected = null;

        //실제 데이터
        Article article = articleService.save(form);

        //검증
        assertEquals(article, expected);
    }


    @Test
    void show_success() {
        //예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "가가가","1111");

        //실제 데이터
        Article article = articleService.show(id);

        //예상과 실제 데이터 검증
        assertEquals(article.toString(),expected.toString());
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        Long id = -1L;

        Article expected = null;

        Article article = articleService.show(id);

        assertEquals(expected, article);
    }

    @Test
    void index() {
        //실제 데이터
        Article a = new Article(1L ,"가가가","1111");
        Article b = new Article(2L ,"나나나", "2222");
        Article c = new Article(3L ,"다다다", "3333");
        List<Article> exteced = new ArrayList<Article>(Arrays.asList(a,b,c));

        //예상 데이터
        List<Article> articles = articleService.index();

        //실제와 예상 데이터 검증
        assertEquals(exteced.toString() ,articles.toString());

    }

    @Transactional
    @Test
    void update_성공_존재하는_id와_title과_content가_있는_dto_입력() {
        // 예상 데이터
        Long id = 3L;
        String title = "가가가";
        String content = "1111";

        ArticleForm form = new ArticleForm(id, title, content);

        Article expected = articleService.update(3L, new ArticleForm(3L, "가가가", "1111"));

        //실제 데이터
        Article article = articleService.update(id, form);

        //비교와 검증
        assertEquals(article.toString(),expected.toString());
    }

    @Transactional
    @Test
    void update_성공_존재하는_id와_title만_있는_dto_입력() {
        // 예상 데이터
        Long id = 3L;
        String title = "가가가";

        ArticleForm form = new ArticleForm(id, title, null);

        Article expected = articleService.update(3L, new ArticleForm(3L, "가가가", null));

        //실제 데이터
        Article article = articleService.update(id, form);

        //비교와 검증
        assertEquals(article.toString(),expected.toString());
    }

    @Test
    void update_실패_존재하지_않는_id의_dto_입력(){
        Long id = 4L;
        String title = "가가가";
        String content = "1111";

        ArticleForm form = new ArticleForm(3L, title, content);

        Article expected = null;

        //실제 데이터
        Article article = articleService.update(id, form);

        //비교와 검증
        assertEquals(article, expected);
    }

    @Transactional
    @Test
    void delete_성공_존재하는_id값_입력(){
        Long id = 3L;
        String title = "다다다";
        String content = "3333";

        Article expected = new Article(id, title, content);

        Article article = articleService.delete(id);

        assertEquals(expected, article);
    }

    @Test
    void delete_실패_존재하지않는_id값_입력(){
        Long id = -1L;

        Article expected = null;

        Article article = articleService.delete(id);

        assertEquals(expected, article);
    }
}
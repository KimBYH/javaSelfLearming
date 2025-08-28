package com.example.javaSelfLearming.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 자동적으로 생성자를 만들어주는 어노테이션
@NoArgsConstructor // 기본 생성자를 만들어줌.
@ToString
@Getter
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동 생성
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    public void patch(Article article){
        if (article.title != null) {
           this.title = article.title;
        }
        if (article.content != null) {
            this.content = article.content;
        }
    }

}

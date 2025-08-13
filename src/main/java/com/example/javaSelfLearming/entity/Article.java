package com.example.javaSelfLearming.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 자동적으로 생성자를 만들어주는 어노테이션
@NoArgsConstructor // 기본 생성자를 만들어줌.
@ToString
@Entity
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

}

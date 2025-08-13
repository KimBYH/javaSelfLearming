package com.example.javaSelfLearming.repository;

import com.example.javaSelfLearming.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}

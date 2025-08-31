package com.example.javaSelfLearming.repository;

import com.example.javaSelfLearming.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    //특정 게시글의 모든 댓글 조회
    @Query(value = "select * from comment where article_id = :articleId",
    nativeQuery = true)
    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    //특정 닉네임의 모든 댓글 조회
    //@Query(value = "select * from comment where nickname : nickname",
    //nativeQuery = true)
    List<Comment> findByNickname( String nickname);
}

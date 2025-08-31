package com.example.javaSelfLearming.entity;

import com.example.javaSelfLearming.dto.CommentDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Entity
@Slf4j
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String body;

    public static Comment createComment(CommentDto dto, Article article){
        log.info("test -> article : {}, dto : {} ", article.getId(), dto.getArticleId());
        if (dto.getId() != null){
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야합니다.");
        }
        if (dto.getArticleId() != article.getId()){
            throw new IllegalArgumentException("댓글 생성 실패! articleId가 없어야합니다.");
        }

        return new Comment(dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody());
    }

    public void patch(CommentDto dto) {
        if (this.id != dto.getId()) {
            throw new IllegalArgumentException("댓글 수정 실패!" + "잘못된 id가 입력되었습니다.");
        }
        if (dto.getNickname() != null) {
            this.nickname = dto.getNickname();
        }
        if (dto.getBody() != null) {
            this.body = dto.getBody();
        }
    }
}

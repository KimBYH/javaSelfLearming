package com.example.javaSelfLearming.service;

import com.example.javaSelfLearming.dto.CommentDto;
import com.example.javaSelfLearming.entity.Article;
import com.example.javaSelfLearming.entity.Comment;
import com.example.javaSelfLearming.repository.ArticleRepository;
import com.example.javaSelfLearming.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> getComments(Long articleId){
        /*리팩터링 전
        List<Comment> comments = commentRepository.findByArticleId(articleId);

        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i < comments.size(); i++) {
            Comment c = comments.get(i);
            CommentDto dto = CommentDto.createCommentDto(c);
            dtos.add(dto);
        }
         */

        // 리팩터링 후
        return commentRepository.findByArticleId(articleId).stream().
                map(comment -> CommentDto.createCommentDto(comment)).collect(Collectors.toList());
    }

    public CommentDto create(Long articleId, CommentDto dto) {
        //게시글 조회
        Article article  = articleRepository.findById(articleId).orElseThrow(() ->
                new IllegalArgumentException("댓글 생성 실패!" + "대상 게시글이 없습니다."));

        //댓글 엔티티 생성
        Comment comment = Comment.createComment(dto,article);

        //댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);

        //DTO로 변환해 반환
        CommentDto commentDto = CommentDto.createCommentDto(created);

        return commentDto;
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto){
        //Comment 객체 불러오기 예외처리
        Comment target = commentRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("댓글 수정 실패!" + "대상 댓글이 존재하지 않습니다."));

        //댓글 수정
        target.patch(dto);

        //DB로 갱신
        Comment updated = commentRepository.save(target);

        //댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id){
        // 객체 불러오기 예외처리
        Comment target = commentRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("댓글 삭제 실패! 대상 댓글이 존재하지 않습니다."));

        //DB 삭제 갱신
        commentRepository.delete(target);

        //삭제된 댓글 외에 target의 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }
}

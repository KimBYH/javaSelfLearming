package com.example.javaSelfLearming.service;

import com.example.javaSelfLearming.dto.CommentDto;
import com.example.javaSelfLearming.repository.ArticleRepository;
import com.example.javaSelfLearming.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

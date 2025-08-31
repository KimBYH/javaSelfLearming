package com.example.javaSelfLearming.controller;

import com.example.javaSelfLearming.dto.CommentDto;
import com.example.javaSelfLearming.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable("articleId") Long articleId) {
        List<CommentDto> dtos = commentService.getComments(articleId);

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

}

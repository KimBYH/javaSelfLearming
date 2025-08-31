package com.example.javaSelfLearming.controller;

import com.example.javaSelfLearming.dto.CommentDto;
import com.example.javaSelfLearming.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable("articleId") Long articleId,
                                             @RequestBody CommentDto dto){
        CommentDto commentDto = commentService.create(articleId, dto);

        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }

    @PatchMapping("/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto){
        CommentDto commentDto = commentService.update(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(commentDto);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        CommentDto deleted = commentService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }

}

package com.Konopka.eCommerce.controllers;


import com.Konopka.eCommerce.models.Comment;
import com.Konopka.eCommerce.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {


    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/Comment")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        return commentService.createComment(comment);
    }


    @DeleteMapping("/comment")
    public ResponseEntity<Integer> deleteComment(@RequestBody Integer commentId) {
        return commentService.deleteComment(commentId);
    }


    @GetMapping("/comment/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable  Integer commentId) {
        return commentService.getCommentById(commentId);
    }


    @GetMapping("/comment/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentByUserId(@PathVariable  Integer userId) {
        return commentService.getAllCommentsByUser(userId);
    }

    @GetMapping("/comment/product/{productId}")
    public ResponseEntity<List<Comment>> getCommentsByProductId(@PathVariable  Integer productId) {
        return commentService.getAllCommentsByProduct(productId);
    }

}

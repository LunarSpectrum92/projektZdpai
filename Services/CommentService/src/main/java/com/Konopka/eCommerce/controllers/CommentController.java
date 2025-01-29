package com.Konopka.eCommerce.controllers;


import com.Konopka.eCommerce.models.Comment;
import com.Konopka.eCommerce.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }




    //create comment
    @PostMapping("/comment")
    public ResponseEntity<Comment> addComment(@Valid @RequestBody Comment comment) {
        return commentService.createComment(comment);
    }

    //delete comment
    @DeleteMapping("/comment")
    public ResponseEntity<Integer> deleteComment(@RequestBody Integer commentId) {
        return commentService.deleteComment(commentId);
    }

    //get comment by id
    @GetMapping("/comment/{commentId}")
    public ResponseEntity<Comment> getComment(@PathVariable Integer commentId) {
        return commentService.getCommentById(commentId);
    }

    //get comment by user id
    @GetMapping("/comment/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentByUserId(@PathVariable  Integer userId) {
        return commentService.getAllCommentsByUser(userId);
    }

    //getCommentsByProductId
    @GetMapping("/comment/product/{productId}")
    public ResponseEntity<List<Comment>> getCommentsByProductId(@PathVariable  Integer productId) {
        return commentService.getAllCommentsByProduct(productId);
    }


}

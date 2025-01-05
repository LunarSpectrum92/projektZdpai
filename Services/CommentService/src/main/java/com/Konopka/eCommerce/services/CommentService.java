package com.Konopka.eCommerce.services;

import com.Konopka.eCommerce.models.Comment;
import com.Konopka.eCommerce.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {


    CommentRepository commentRepository;


    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }



    public ResponseEntity<Comment> createComment(Comment comment) {
        if(commentRepository.findById(comment.getCommentId()).isPresent()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.CREATED);
    }












    public ResponseEntity<Integer> deleteComment(Integer commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if(comment.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        commentRepository.delete(comment.get());
        return new ResponseEntity<>(commentId, HttpStatus.OK);
    }



    public ResponseEntity<Comment> getCommentById(Integer commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        return comment.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }




    public ResponseEntity<List<Comment>> getAllCommentsByUser(Integer userId) {
        List<Comment> comments = commentRepository.findAllByUserId(userId);
        if(comments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(comments, HttpStatus.OK);

    }


    public ResponseEntity<List<Comment>> getAllCommentsByProduct(Integer productId) {
        List<Comment> comments = commentRepository.findAllByProductId(productId);
        if(comments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }





}

package com.Konopka.eCommerce.services;

import com.Konopka.eCommerce.DTO.CommentDTO;
import com.Konopka.eCommerce.DTO.CommentDtoMapper;
import com.Konopka.eCommerce.DTO.CommentRequest;
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
    CommentDtoMapper commentDtoMapper;


    @Autowired
    public CommentService(CommentRepository commentRepository, CommentDtoMapper commentDtoMapper) {
        this.commentRepository = commentRepository;
        this.commentDtoMapper = commentDtoMapper;
    }



    public ResponseEntity<CommentDTO> createComment(CommentRequest commentRequest) {

        Comment comment = Comment.builder()
                .commentBody(commentRequest.commentBody())
                .score(commentRequest.score())
                .userId(commentRequest.userId())
                .userId(commentRequest.userId())
                .productId(commentRequest.productId())
                .build();



        commentRepository.save(comment);
        return new ResponseEntity<>(commentDtoMapper.commentDTOMapper(comment), HttpStatus.CREATED);
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


    public ResponseEntity<List<CommentDTO>> getAllCommentsByProduct(Integer productId) {
        List<Comment> comments = commentRepository.findAllByProductId(productId);
        if(comments.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<CommentDTO> commentsMapped = comments.stream()
                .map(commentDtoMapper::commentDTOMapper)
                .toList();



        return new ResponseEntity<>(commentsMapped, HttpStatus.OK);
    }





}

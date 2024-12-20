package com.Konopka.eCommerce.CommentService.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Konopka.eCommerce.CommentService.models.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findAllByUserId(Integer id);

    List<Comment> findAllByProductId(Integer id);

}

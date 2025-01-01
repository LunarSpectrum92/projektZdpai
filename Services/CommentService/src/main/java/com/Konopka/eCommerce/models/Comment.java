package com.Konopka.eCommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Ensure proper ID generation strategy
    private Integer commentId;

    @NotEmpty(message = "Comment body must not be empty")
    @Size(max = 1000, message = "Comment body must not exceed 1000 characters")  // Added size validation for the body
    private String commentBody;

    @CreatedDate  // Automatically handled by Spring Data JPA (ensure @EnableJpaAuditing is in config)
    private Timestamp commentDate;

    @Min(value = 1, message = "Score must be at least 1")
    @Max(value = 5, message = "Score must be at most 5")
    private Integer score;

    private Integer userId;

    private Integer productId;

}

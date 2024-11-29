package com.Konopka.eCommerce.PhotoService.Repository;


import com.Konopka.eCommerce.PhotoService.Models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Integer> {
}

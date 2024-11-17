package com.gulbi.Backend.domain.rental.product.repository;

import com.gulbi.Backend.domain.rental.product.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long > {
}
package com.gulbi.Backend.domain.rental.product.repository;

import com.gulbi.Backend.domain.rental.product.dto.ProductImageDto;
import com.gulbi.Backend.domain.rental.product.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long > {
    @Query("SELECT new com.gulbi.Backend.domain.rental.product.dto.ProductImageDto(i.id, i.product.id, i.url) " +
            "FROM Image i WHERE i.product.id = :productId")
    public List<ProductImageDto> findByImageWithProduct(@Param("productId") Long productId);



}

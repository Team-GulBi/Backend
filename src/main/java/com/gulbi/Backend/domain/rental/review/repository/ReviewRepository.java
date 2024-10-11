package com.gulbi.Backend.domain.rental.review.repository;

import com.gulbi.Backend.domain.rental.review.dto.ReviewWithAvgProjection;
import com.gulbi.Backend.domain.rental.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    @Query("SELECT r FROM Review r WHERE r.product.id = :productId")
    public Optional<List<Review>> bringAllReviewByProductId(@Param("productId") Long productId);

//    @Query("SELECT r,(SELECT AVG(r.rating) FROM Review r WHERE r.product.id = :productId) FROM Review r WHERE r.product.id = :productId")
//    public Optional<List<ReviewWithAvgProjection>> bringAllReviewAndAvgByProductId(@Param("productId") Long productId);
    @Query("SELECT r.id AS id, r.rating AS rating, r.content AS content, AVG(r.rating) AS averageRating " +
        "FROM Review r WHERE r.product.id = :productId GROUP BY r.id")
    public Optional<List<ReviewWithAvgProjection>> bringAllReviewAndAvgByProductId(@Param("productId") Long productId);


}

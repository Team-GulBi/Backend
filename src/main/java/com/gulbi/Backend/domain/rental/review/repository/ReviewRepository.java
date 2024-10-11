package com.gulbi.Backend.domain.rental.review.repository;

import com.gulbi.Backend.domain.rental.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
}

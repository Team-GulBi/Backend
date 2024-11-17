package com.gulbi.Backend.domain.rental.review.service;

import com.gulbi.Backend.domain.rental.review.dto.ReviewCreateRequestDto;
import com.gulbi.Backend.domain.rental.review.dto.ReviewWithAvgProjection;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    public void registerReview(ReviewCreateRequestDto request);
    public List<ReviewWithAvgProjection> bringAllReview(Long productId);
}

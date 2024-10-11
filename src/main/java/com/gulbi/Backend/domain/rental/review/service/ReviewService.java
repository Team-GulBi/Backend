package com.gulbi.Backend.domain.rental.review.service;

import com.gulbi.Backend.domain.rental.review.dto.ReviewCreateRequest;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    public void registerReview(ReviewCreateRequest request);
}

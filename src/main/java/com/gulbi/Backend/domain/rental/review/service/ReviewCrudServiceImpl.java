package com.gulbi.Backend.domain.rental.review.service;

import com.gulbi.Backend.domain.rental.review.dto.ReviewUpdateRequestDto;
import com.gulbi.Backend.domain.rental.review.dto.ReviewWithAvgProjection;
import com.gulbi.Backend.domain.rental.review.entity.Review;
import com.gulbi.Backend.domain.rental.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCrudServiceImpl implements ReviewCrudService{

    private final ReviewRepository reviewRepository;

    @Override
    public void saveReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public List<ReviewWithAvgProjection> getReviewWithRateAvg(Long productId) {
        return reviewRepository.findAllReviewAndAvgByProductId(productId);
    }

    @Override
    public void deleteReview(Long reviewId){
        reviewRepository.deleteReviewByReviewId(reviewId);
    }
    @Override
    public void updateReview(ReviewUpdateRequestDto reviewUpdateRequestDto){
        String content = reviewUpdateRequestDto.getContent();
        Integer rating = reviewUpdateRequestDto.getRating();
        Long reviewId = reviewUpdateRequestDto.getReviewId();
        reviewRepository.updateReviewInfo(rating, content, reviewId);
    }
}

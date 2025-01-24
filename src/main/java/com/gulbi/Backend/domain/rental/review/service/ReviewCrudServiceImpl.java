package com.gulbi.Backend.domain.rental.review.service;

import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.service.product.crud.ProductCrudService;
import com.gulbi.Backend.domain.rental.review.code.ReviewErrorCode;
import com.gulbi.Backend.domain.rental.review.dto.ReviewUpdateRequestDto;
import com.gulbi.Backend.domain.rental.review.dto.ReviewWithAvgProjection;
import com.gulbi.Backend.domain.rental.review.entity.Review;
import com.gulbi.Backend.domain.rental.review.exception.ReviewException;
import com.gulbi.Backend.domain.rental.review.repository.ReviewRepository;
import com.gulbi.Backend.global.error.ExceptionMetaData;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCrudServiceImpl implements ReviewCrudService {

    private final ReviewRepository reviewRepository;
    private final ProductCrudService productCrudService;

    @Override
    public void saveReview(Review review) {
        try {
            reviewRepository.save(review);
        } catch (DataIntegrityViolationException | JpaSystemException | PersistenceException e) {
            throw createDatabaseErrorException(review);
        } catch (IllegalArgumentException e) {
            throw createMissingReviewFieldException(review);
        }
    }

    @Override
    public List<ReviewWithAvgProjection> getReviewWithRateAvg(Long productId) {
        return reviewRepository.findAllReviewAndAvgByProductId(productId);
    }

    @Override
    public void deleteReview(Long reviewId) {
        reviewRepository.deleteReviewByReviewId(reviewId);
    }

    @Override
    public void updateReview(ReviewUpdateRequestDto reviewUpdateRequestDto) {
        reviewRepository.updateReviewInfo(
                reviewUpdateRequestDto.getRating(),
                reviewUpdateRequestDto.getContent(),
                reviewUpdateRequestDto.getReviewId()
        );
    }

    @Override
    public void removeAllReviewsFromProductId(Long productId) {
        reviewRepository.deleteAllReviewsByProduct(resolveProduct(productId));
    }

    private Product resolveProduct(Long productId) {
        return productCrudService.getProductById(productId);
    }

    private ReviewException.DatabaseErrorException createDatabaseErrorException(Review review) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(review, this.getClass().getName());
        return new ReviewException.DatabaseErrorException(ReviewErrorCode.DATABASE_ERROR, exceptionMetaData);
    }

    private ReviewException.MissingReviewFiledException createMissingReviewFieldException(Review review) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(review, this.getClass().getName());
        return new ReviewException.MissingReviewFiledException(ReviewErrorCode.DATABASE_ERROR,exceptionMetaData);
    }
}

package com.gulbi.Backend.domain.rental.review.service;

import com.gulbi.Backend.domain.rental.product.code.ProductErrorCode;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.exception.ProductException;
import com.gulbi.Backend.domain.rental.product.service.product.crud.ProductCrudService;
import com.gulbi.Backend.domain.rental.review.code.ReviewErrorCode;
import com.gulbi.Backend.domain.rental.review.dto.ReviewUpdateRequestDto;
import com.gulbi.Backend.domain.rental.review.dto.ReviewWithAvgProjection;
import com.gulbi.Backend.domain.rental.review.entity.Review;
import com.gulbi.Backend.domain.rental.review.exception.ReviewException;
import com.gulbi.Backend.domain.rental.review.repository.ReviewRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewCrudServiceImpl implements ReviewCrudService{

    private final ReviewRepository reviewRepository;
    private final ProductCrudService productCrudService;
    @Override
    public void saveReview(Review review) {
        try{
        reviewRepository.save(review);}
        catch (DataIntegrityViolationException | JpaSystemException | PersistenceException e){
            throw new ReviewException.DatabaseErrorException(ReviewErrorCode.DATABASE_ERROR);
        } catch (IllegalArgumentException e){
            throw new ReviewException.MissingReviewFiledException(ReviewErrorCode.DATABASE_ERROR);
        }
    }

    @Override
    public List<ReviewWithAvgProjection> getReviewWithRateAvg(Long productId) {
        List<ReviewWithAvgProjection> reviews = reviewRepository.findAllReviewAndAvgByProductId(productId);
//        if(reviews.isEmpty()){
//            throw new ReviewException.ReviewNotFoundException(ReviewErrorCode.REVIEW_NOT_FOUND);
//        } 리뷰가 없는 상품도 있을 수 있는데 예외처리를 하니까 처세가 안됨.
        return reviews;
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

    @Override
    public void removeAllReviewsFromProductId(Long productId) {
        reviewRepository.deleteAllReviewsByProduct(resolveProduct(productId));
    }

    private Product resolveProduct(Long productId){
        return productCrudService.getProductById(productId);
    }
}

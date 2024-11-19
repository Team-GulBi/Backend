package com.gulbi.Backend.domain.rental.review.service;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.repository.ProductRepository;
import com.gulbi.Backend.domain.rental.product.service.ProductCrudService;
import com.gulbi.Backend.domain.rental.review.dto.ReviewCreateRequestDto;
import com.gulbi.Backend.domain.rental.review.dto.ReviewWithAvgProjection;
import com.gulbi.Backend.domain.rental.review.entity.Review;
import com.gulbi.Backend.domain.rental.review.factory.ReviewFactory;
import com.gulbi.Backend.domain.rental.review.repository.ReviewRepository;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.repository.UserRepository;
import com.gulbi.Backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewCrudService reviewCrudService;
    private final ReviewFactory reviewFactory;
    private final UserService userService;
    private final ProductCrudService productCrudService;

    @Override
    public void registerReview(ReviewCreateRequestDto review) {
        Review review1 = createReviewWithUserAndProduct(review);
        reviewCrudService.saveReview(review1);
    }

    @Override
    public List<ReviewWithAvgProjection> bringAllReview(Long productId) {
        List<ReviewWithAvgProjection> reviewWithAvg = reviewCrudService.findReviewWithRateAvg(productId);
        return reviewWithAvg;
    }

    @Override
    public Review createReviewWithUserAndProduct(ReviewCreateRequestDto review) {
        return reviewFactory.createWithRegisterRequest(review);
    }

}

package com.gulbi.Backend.domain.rental.review.service;

import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.repository.ProductRepository;
import com.gulbi.Backend.domain.rental.review.dto.ReviewCreateRequestDto;
import com.gulbi.Backend.domain.rental.review.dto.ReviewWithAvgProjection;
import com.gulbi.Backend.domain.rental.review.entity.Review;
import com.gulbi.Backend.domain.rental.review.repository.ReviewRepository;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    private final UserRepository userRepository; // jwt연동 되면 없앨거임.
    @Override
    public void registerReview(ReviewCreateRequestDto request) {

        User user = User.builder().email("abua100").phoneNumber("0010388293").nickname("zsexs").password("2gdd1!").build();  // user관련 태호가 머지해주기 전에는 임시로 만들어서 쓸거임. 추후 머지 되면 없어짐.
        userRepository.save(user); // user관련 태호가 머지해주기 전에는 임시로 만들어서 쓸거임. 추후 머지 되면 없어짐.

        Long productId = request.getProductId();
        String content = request.getContent();
        Integer rating = request.getRating();

        Product product = productRepository.findById(productId).orElseThrow();

        Review review = Review.builder().product(product).rating(rating).content(content).user(user).build();

        reviewRepository.save(review);
    }

    @Override
    public List<ReviewWithAvgProjection> bringAllReview(Long productId) {
        List<ReviewWithAvgProjection> reviewWithAvg = reviewRepository.bringAllReviewAndAvgByProductId(productId);

        if (reviewWithAvg.isEmpty()) {
            System.out.println("No reviews found for product ID: " + productId);
            return reviewWithAvg; // 비어있는 리스트를 반환
        }

        System.out.println(reviewWithAvg.get(0).getContent());
        return reviewWithAvg;
    }
}

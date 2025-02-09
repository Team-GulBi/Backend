package com.gulbi.Backend.domain.rental.product.service.product.search;

import com.gulbi.Backend.domain.rental.product.code.ProductErrorCode;
import com.gulbi.Backend.domain.rental.product.dto.ProductImageDtoCollection;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductSearchRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.response.ProductDetailResponseDto;
import com.gulbi.Backend.domain.rental.product.exception.ProductException;
import com.gulbi.Backend.domain.rental.product.service.image.ImageCrudService;
import com.gulbi.Backend.domain.rental.product.service.product.crud.ProductCrudService;
import com.gulbi.Backend.domain.rental.product.service.product.search.strategy.search.ProductSearchStrategy;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import com.gulbi.Backend.domain.rental.review.dto.ReviewWithAvgProjection;
import com.gulbi.Backend.domain.rental.review.service.ReviewService;
import com.gulbi.Backend.domain.user.dto.ProfileResponseDto;
import com.gulbi.Backend.domain.user.entity.Profile;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.service.ProfileService;
import com.gulbi.Backend.domain.user.service.UserService;
import com.gulbi.Backend.global.error.ExceptionMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {
    private final String className = this.getClass().getName();
    private final ProductCrudService productCrudService;
    private final ImageCrudService imageCrudService;
    private final ReviewService reviewService;
    private final UserService userService;
    private final ProfileService profileService;
    private final Map<String, ProductSearchStrategy> productSearchStrategies;

    @Autowired
    public ProductSearchServiceImpl(ProductCrudService productCrudService, ImageCrudService imageCrudService, ReviewService reviewService, UserService userService, ProfileService profileService, Map<String, ProductSearchStrategy> productSearchStrategies) {
        this.productCrudService = productCrudService;
        this.imageCrudService = imageCrudService;
        this.reviewService = reviewService;
        this.userService = userService;
        this.profileService = profileService;
        this.productSearchStrategies = productSearchStrategies;
    }

    @Override
    public List<ProductOverViewResponse> searchProductByQuery(ProductSearchRequestDto productSearchRequestDto) {
        String detail = productSearchRequestDto.getDetail().trim();
        String query = productSearchRequestDto.getQuery();

        // 예외를 처리하는 부분을 private 메서드로 분리
        ProductSearchStrategy productSearchStrategy = getProductSearchStrategy(detail, productSearchRequestDto);

        return productSearchStrategy.search(query);
    }

    @Override
    public ProductDetailResponseDto getProductDetail(Long productId) {
        ProductDto product = getProductById(productId);
        ProductImageDtoCollection imageList = getProductImagesByProductId(productId);
        List<ReviewWithAvgProjection> reviewWithAvg = getProductReviewsByProductId(productId);
        ImageUrl userPhoto = getImageOfUser();
        String userNickname = getUserName();
        return ProductDetailResponseDto.of(product, imageList, reviewWithAvg,userPhoto,userNickname);
    }

    private ProductDto getProductById(Long productId) {
        return productCrudService.getProductDtoById(productId);
    }

    private ProductImageDtoCollection getProductImagesByProductId(Long productId) {
        return imageCrudService.getImageByProductId(productId);
    }

    private List<ReviewWithAvgProjection> getProductReviewsByProductId(Long productId) {
        return reviewService.getAllReview(productId);
    }


    private User getUser(){
        return userService.getAuthenticatedUser();
    }

    private ProfileResponseDto getProfile(Long userId){
        return profileService.getProfile(userId);
    }

    private ImageUrl getImageOfUser(){
        User user = getUser();
        ProfileResponseDto profile = getProfile(user.getId());
        return ImageUrl.of(profile.getImage());
    }

    private String getUserName(){
        return getUser().getNickname();
    }

    // 예외를 처리하는 로직을 private 메서드로 분리
    private ProductSearchStrategy getProductSearchStrategy(String detail, ProductSearchRequestDto productSearchRequestDto) {
        return Optional.ofNullable(productSearchStrategies.get(detail))
                .orElseThrow(() -> createInvalidProductSearchDetailException(productSearchRequestDto));
    }

    // 예외를 생성하는 메서드
    private ProductException.InvalidProductSearchDetailException createInvalidProductSearchDetailException(Object args) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData.Builder().args(args).className(className).responseApiCode(ProductErrorCode.UNSUPPORTED_SEARCH_CONDITION).build();
        return new ProductException.InvalidProductSearchDetailException(exceptionMetaData);
    }
}

package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.dto.ProductImageDtoCollection;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.response.ProductDetailResponseDto;
import com.gulbi.Backend.domain.rental.product.service.image.ImageService;
import com.gulbi.Backend.domain.rental.review.dto.ReviewWithAvgProjection;
import com.gulbi.Backend.domain.rental.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService{
    private final ProductCrudService productCrudService;
    private final ImageService imageService;
    private final ReviewService reviewService;
    @Override
    public ProductDetailResponseDto getProductDetail(Long productId) {
        ProductDto product = getProductById(productId);
        ProductImageDtoCollection imageList = getProductImagesByProductId(productId);
        List<ReviewWithAvgProjection> reviewWithAvg = getProductReviewsByProductId(productId);
        return ProductDetailResponseDto.of(product, imageList, reviewWithAvg);
    }

    private ProductDto getProductById(Long productId) {
        return productCrudService.getProductDtoById(productId);
    }

    private ProductImageDtoCollection getProductImagesByProductId(Long productId) {
        return imageService.getImageByProductId(productId);
    }

    private List<ReviewWithAvgProjection> getProductReviewsByProductId(Long productId) {
        return reviewService.getAllReview(productId);
    }
}

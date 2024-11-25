package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.dto.ProductImageDtoCollection;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductSearchRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.response.ProductDetailResponseDto;
import com.gulbi.Backend.domain.rental.product.service.image.ImageService;
import com.gulbi.Backend.domain.rental.product.service.product.strategy.search.ProductSearchStrategy;
import com.gulbi.Backend.domain.rental.review.dto.ReviewWithAvgProjection;
import com.gulbi.Backend.domain.rental.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {
    private final ProductCrudService productCrudService;
    private final ImageService imageService;
    private final ReviewService reviewService;
    private final Map<String, ProductSearchStrategy> productSearchStrategies;
    @Autowired
    public ProductSearchServiceImpl(ProductCrudService productCrudService, ImageService imageService, ReviewService reviewService, Map<String, ProductSearchStrategy> productSearchStrategies) {
        this.productCrudService = productCrudService;
        this.imageService = imageService;
        this.reviewService = reviewService;
        this.productSearchStrategies = productSearchStrategies;
    }

    @Override
    public List<ProductOverViewResponse> searchProductByQuery(ProductSearchRequestDto productSearchRequestDto) {
        String detail = productSearchRequestDto.getDetail().trim();
        String query = productSearchRequestDto.getQuery();
        ProductSearchStrategy productSearchStrategy = productSearchStrategies.get(detail);
        return productSearchStrategy.search(query);
    }

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

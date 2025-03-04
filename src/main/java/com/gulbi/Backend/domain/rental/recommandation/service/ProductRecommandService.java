package com.gulbi.Backend.domain.rental.recommandation.service;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRecommandService {
    List<ProductOverViewResponse> getRealTimePopularProducts();
    List<ProductOverViewResponse> getRecentRegistrationProducts(Pageable pageable, LocalDateTime lastCreatedAt);
    List<ProductOverViewResponse> getRecentProductByCategory(Long bCategoryId, Long mCategoryId, Long sCategoryId, LocalDateTime lastCreatedAt, Pageable pageable);
    List<ProductOverViewResponse> getPersonalizedRecommendationProducts();

}

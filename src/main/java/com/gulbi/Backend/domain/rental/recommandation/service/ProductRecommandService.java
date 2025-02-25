package com.gulbi.Backend.domain.rental.recommandation.service;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;

import java.util.List;

public interface ProductRecommandService {
    List<ProductOverViewResponse> getRealTimePopularProducts();
    List<ProductOverViewResponse> getRecentRegistrationProducts();
}

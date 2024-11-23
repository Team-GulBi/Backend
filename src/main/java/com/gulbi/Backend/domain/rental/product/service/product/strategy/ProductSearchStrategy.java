package com.gulbi.Backend.domain.rental.product.service.product.strategy;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductSearchRequestDto;

import java.util.List;

public interface ProductSearchStrategy {
    public List<ProductOverViewResponse> search(String query);
}

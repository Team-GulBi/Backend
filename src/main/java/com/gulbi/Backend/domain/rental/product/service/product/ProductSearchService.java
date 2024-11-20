package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductSearchRequestDto;

import java.util.List;

public interface ProductSearchService {
    public List<ProductOverViewResponse> searchProductByQuery(ProductSearchRequestDto productSearchRequestDto);
}

package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.dto.product.response.ProductDetailResponseDto;

public interface ProductDetailService {
    public ProductDetailResponseDto getProductDetail(Long productId);
}

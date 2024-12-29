package com.gulbi.Backend.domain.rental.product.dto.product.request;

import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;

public class ProductImageUpdateRequestDto {
    private final ProductImageCollection productImageCollection;
    private Long productId;

    private ProductImageUpdateRequestDto(ProductImageCollection productImageCollection) {
        this.productImageCollection = productImageCollection;
    }

//    public static ProductImageUpdateRequestDto
}

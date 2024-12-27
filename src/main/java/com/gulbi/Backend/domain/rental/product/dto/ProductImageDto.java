package com.gulbi.Backend.domain.rental.product.dto;

import lombok.Getter;

@Getter
public class ProductImageDto {
    private final Long id;
    private final Long productId;
    private final String url;

    // Product 객체에서 필요한 필드를 가져오는 생성자
    public ProductImageDto(Long id, Long productId, String url) {
        this.id = id;
        this.productId = productId;
        this.url = url;
    }
}
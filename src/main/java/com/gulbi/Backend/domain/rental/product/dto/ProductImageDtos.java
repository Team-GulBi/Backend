package com.gulbi.Backend.domain.rental.product.dto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductImageDtos {
    private final List<ProductImageDto> productImages;

    public ProductImageDtos(List<ProductImageDto> productImages) {
        this.productImages = productImages;
    }

    public static ProductImageDtos of(List<ProductImageDto> images){
        return new ProductImageDtos(images);
    }

    public List<String> toUrlList() {
        return productImages.stream()
                .map(ProductImageDto::getUrl)
                .collect(Collectors.toList());
    }
}

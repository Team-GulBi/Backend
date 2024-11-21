package com.gulbi.Backend.domain.rental.product.dto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductImageDtoCollection {
    private final List<ProductImageDto> productImages;

    public ProductImageDtoCollection(List<ProductImageDto> productImages) {
        this.productImages = productImages;
    }

    public static ProductImageDtoCollection of(List<ProductImageDto> images){
        return new ProductImageDtoCollection(images);
    }

    public List<String> toUrlList() {
        return productImages.stream()
                .map(ProductImageDto::getUrl)
                .collect(Collectors.toList());
    }
}

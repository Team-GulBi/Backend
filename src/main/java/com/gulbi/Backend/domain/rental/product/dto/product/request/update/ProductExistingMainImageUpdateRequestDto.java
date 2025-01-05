package com.gulbi.Backend.domain.rental.product.dto.product.request.update;

import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import lombok.Getter;

@Getter
public class ProductExistingMainImageUpdateRequestDto {
    private ImageUrl mainImageUrl;


    private ProductExistingMainImageUpdateRequestDto() {}

    private ProductExistingMainImageUpdateRequestDto(String mainImageUrl) {
        this.mainImageUrl = ImageUrl.of(mainImageUrl);
    }

    public static ProductExistingMainImageUpdateRequestDto of(String mainImageUrl) {
        return new ProductExistingMainImageUpdateRequestDto(mainImageUrl);
    }
}

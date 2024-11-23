package com.gulbi.Backend.domain.rental.product.dto.product.request;

import lombok.Getter;

@Getter
public class ProductCategoryUpdateRequestDto {
    private final String bCategoryId;
    private final String mCategoryId;
    private final String sCategoryId;

    private ProductCategoryUpdateRequestDto(String bCategoryId, String mCategoryId, String sCategoryId){
        this.bCategoryId = bCategoryId;
        this.mCategoryId = mCategoryId;
        this.sCategoryId = sCategoryId;
    }

    public static ProductCategoryUpdateRequestDto of(String bCategoryId, String mCategoryId, String sCategoryId){
        return new ProductCategoryUpdateRequestDto(bCategoryId, mCategoryId, sCategoryId);
    }
}

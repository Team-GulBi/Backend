package com.gulbi.Backend.domain.rental.product.dto;

import com.gulbi.Backend.domain.rental.product.entity.Category;

public class CategoryDto {
    private final Category bCategoryId;
    private final Category mCategoryId;
    private final Category sCategoryId;

    private CategoryDto(Category bCategoryId, Category mCategoryId, Category sCategoryId){
        this.bCategoryId = bCategoryId;
        this.mCategoryId = mCategoryId;
        this.sCategoryId = sCategoryId;
    }

    public static CategoryDto of(Category bCategoryId,Category mCategoryId,Category sCategoryId){
        return new CategoryDto(bCategoryId, mCategoryId, sCategoryId);
    }
}

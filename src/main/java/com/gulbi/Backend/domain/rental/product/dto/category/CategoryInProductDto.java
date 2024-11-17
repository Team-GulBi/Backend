package com.gulbi.Backend.domain.rental.product.dto.category;

import com.gulbi.Backend.domain.rental.product.entity.Category;
import lombok.Getter;

@Getter
public class CategoryInProductDto {
    private final Category bCategoryId;
    private final Category mCategoryId;
    private final Category sCategoryId;

    private CategoryInProductDto(Category bCategoryId, Category mCategoryId, Category sCategoryId){
        this.bCategoryId = bCategoryId;
        this.mCategoryId = mCategoryId;
        this.sCategoryId = sCategoryId;
    }

    public static CategoryInProductDto of(Category bCategoryId, Category mCategoryId, Category sCategoryId){
        return new CategoryInProductDto(bCategoryId, mCategoryId, sCategoryId);
    }
}

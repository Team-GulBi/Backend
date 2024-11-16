package com.gulbi.Backend.domain.rental.product.dto.category;

import com.gulbi.Backend.domain.rental.product.entity.Category;
import lombok.Getter;

@Getter
public class CategoryInProductRegisterDto {
    private final Category bCategoryId;
    private final Category mCategoryId;
    private final Category sCategoryId;

    private CategoryInProductRegisterDto(Category bCategoryId, Category mCategoryId, Category sCategoryId){
        this.bCategoryId = bCategoryId;
        this.mCategoryId = mCategoryId;
        this.sCategoryId = sCategoryId;
    }

    public static CategoryInProductRegisterDto of(Category bCategoryId, Category mCategoryId, Category sCategoryId){
        return new CategoryInProductRegisterDto(bCategoryId, mCategoryId, sCategoryId);
    }
}

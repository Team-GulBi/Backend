package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductDto;

public interface CategoryBusinessService {
    public CategoryInProductDto resolveCategories(String bCategoryId, String mCategoryId, String sCategoryId);
    public CategoryInProductDto resolveCategories(Integer bCategoryId, Integer mCategoryId, Integer sCategoryId);
}

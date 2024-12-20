package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductCategoryUpdateRequestDto;

public interface CategoryBusinessService {
    public CategoryInProductDto resolveCategories(Long bCategoryId, Long mCategoryId, Long sCategoryId);
    public CategoryInProductDto resolveCategories(ProductCategoryUpdateRequestDto productCategoryUpdateRequestDto);
}

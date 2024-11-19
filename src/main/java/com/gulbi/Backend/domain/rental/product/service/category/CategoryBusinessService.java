package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductDto;

public interface CategoryBusinessService {
    public CategoryInProductDto resolveCategories(ProductRegisterRequestDto productRegisterRequestDto);
}

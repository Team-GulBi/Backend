package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductDto;
import com.gulbi.Backend.domain.rental.product.dto.request.ProductRegisterRequestDto;

public interface CategoryBusinessService {
    public CategoryInProductDto resolveCategories(ProductRegisterRequestDto productRegisterRequestDto);
}

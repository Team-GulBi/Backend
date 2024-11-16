package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.dto.CategoryDto;
import com.gulbi.Backend.domain.rental.product.dto.ProductRegisterRequestDto;

public interface CategoryBusinessService {
    public CategoryDto resolveCategories(ProductRegisterRequestDto productRegisterRequestDto);
}

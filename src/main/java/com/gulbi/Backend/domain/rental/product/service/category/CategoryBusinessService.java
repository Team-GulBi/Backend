package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductRegisterDto;
import com.gulbi.Backend.domain.rental.product.dto.request.ProductRegisterRequestDto;

public interface CategoryBusinessService {
    public CategoryInProductRegisterDto resolveCategories(ProductRegisterRequestDto productRegisterRequestDto);
}

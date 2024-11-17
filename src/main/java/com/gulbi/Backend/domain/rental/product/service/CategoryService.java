package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.CategoryDto;
import java.util.List;

public interface CategoryService {
    List<CategoryDto> getBigCategories();
    List<CategoryDto> getBelowCategoriesByParentId(Integer categoryId);
}

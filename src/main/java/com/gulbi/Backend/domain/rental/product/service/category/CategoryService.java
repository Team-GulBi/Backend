package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.dto.CategoryProjection;

import java.util.List;

public interface CategoryService {
    public List<CategoryProjection> getBigCategories();
    public List<CategoryProjection> getBelowCategoriesByParentId(Long categoryId);
}

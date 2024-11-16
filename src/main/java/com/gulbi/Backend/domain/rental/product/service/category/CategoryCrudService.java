package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.dto.CategoryProjection;
import com.gulbi.Backend.domain.rental.product.entity.Category;

import java.util.List;

public interface CategoryCrudService {
    public List<CategoryProjection> getBigCategories();
    public List<CategoryProjection> getBelowCategoriesByParentId(Integer categoryId);
    public Category getCategoryById(Integer categoryId);
}

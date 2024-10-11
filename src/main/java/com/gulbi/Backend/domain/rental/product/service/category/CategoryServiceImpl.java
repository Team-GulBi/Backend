package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.dto.CategoryProjection;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import com.gulbi.Backend.domain.rental.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public List<CategoryProjection> getBigCategories() {

        List<CategoryProjection> bigCategoryList= categoryRepository.findAllNoParent();

        return bigCategoryList;
    }

    @Override
    public List<CategoryProjection> getBelowCategoriesByParentId(Integer categoryId) {
        List<CategoryProjection> belowCategoryList= categoryRepository.findBelowCategory(categoryId);

        return belowCategoryList;
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        return category;
    }
}
package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.code.CategoryErrorCode;
import com.gulbi.Backend.domain.rental.product.dto.category.CategoryProjection;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import com.gulbi.Backend.domain.rental.product.exception.CategoryException;
import com.gulbi.Backend.domain.rental.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryCrudServiceImpl implements CategoryCrudService {

    private final CategoryRepository categoryRepository;
    @Override
    public List<CategoryProjection> getBigCategories() {

        List<CategoryProjection> bigCategoryList= categoryRepository.findAllNoParentProjection();
        if(bigCategoryList.isEmpty()){
            throw new CategoryException.NotInitializedCategoryException(CategoryErrorCode.NOT_INITIALIZED_CATEGORIES);
        }

        return bigCategoryList;
    }

    @Override
    public List<CategoryProjection> getBelowCategoriesByParentId(Integer categoryId) {
        List<CategoryProjection> belowCategoryList= categoryRepository.findBelowCategory(categoryId);
        if(belowCategoryList.isEmpty()){
            throw new CategoryException.CategoryNotFoundException(CategoryErrorCode.NOT_FOUND_CATEGORY);
        }
        return belowCategoryList;
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () ->new CategoryException.CategoryNotFoundException(CategoryErrorCode.NOT_FOUND_CATEGORY)
        );
    }

}

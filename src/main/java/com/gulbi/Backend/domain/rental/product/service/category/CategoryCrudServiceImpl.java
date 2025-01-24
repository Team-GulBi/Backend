package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.code.CategoryErrorCode;
import com.gulbi.Backend.domain.rental.product.dto.category.CategoryProjection;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import com.gulbi.Backend.domain.rental.product.exception.CategoryException;
import com.gulbi.Backend.domain.rental.product.repository.CategoryRepository;
import com.gulbi.Backend.global.error.ExceptionMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryCrudServiceImpl implements CategoryCrudService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryProjection> getBigCategories() {
        List<CategoryProjection> bigCategoryList = categoryRepository.findAllNoParentProjection();
        if (bigCategoryList.isEmpty()) {
            throwNotInitializedCategoryException("Big Categories");
        }
        return bigCategoryList;
    }

    @Override
    public List<CategoryProjection> getBelowCategoriesByParentId(Long categoryId) {
        List<CategoryProjection> belowCategoryList = categoryRepository.findBelowCategory(categoryId);
        if (belowCategoryList.isEmpty()) {
            throwCategoryException(CategoryErrorCode.NOT_FOUND_CATEGORY, "Category with ID: " + categoryId);
        }
        return belowCategoryList;
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(Math.toIntExact(categoryId)).orElseThrow(
                () -> {
                    throwCategoryException(CategoryErrorCode.NOT_FOUND_CATEGORY, "Category ID: " + categoryId);
                    return null; // 이 부분은 사실 필요하지 않음, 예외가 발생하면 여기까지 오지 않음
                }
        );
    }

    private CategoryException.NotInitializedCategoryException throwNotInitializedCategoryException(String fieldValue) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(fieldValue, this.getClass().getName());
        throw new CategoryException.NotInitializedCategoryException(CategoryErrorCode.NOT_INITIALIZED_CATEGORIES, exceptionMetaData);
    }


    private CategoryException.CategoryNotFoundException throwCategoryException(CategoryErrorCode errorCode, String fieldValue) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(fieldValue, this.getClass().getName());
        throw new CategoryException.CategoryNotFoundException(errorCode, exceptionMetaData);
    }
}

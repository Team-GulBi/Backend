package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.CategoryDto;
import com.gulbi.Backend.domain.rental.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> getBigCategories() {
        // 부모가 없는 최상위 대분류 카테고리 조회
        return categoryRepository.findAllNoParent();
    }

    @Override
    public List<CategoryDto> getBelowCategoriesByParentId(Integer categoryId) {
        // 주어진 부모 ID를 가진 하위 카테고리 조회
        return categoryRepository.findBelowCategory(categoryId);
    }
}

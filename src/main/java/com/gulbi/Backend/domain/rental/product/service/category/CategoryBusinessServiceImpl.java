package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductDto;
import com.gulbi.Backend.domain.rental.product.dto.request.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryBusinessServiceImpl implements CategoryBusinessService{
    private final CategoryCrudService categoryCrudService;
    @Override
    public CategoryInProductDto resolveCategories(String bCategoryId, String mCategoryId, String sCategoryId) {
        Category bCategory = categoryCrudService.getCategoryById(Integer.valueOf(bCategoryId));
        Category mCategory = categoryCrudService.getCategoryById(Integer.valueOf(mCategoryId));
        Category sCategory = categoryCrudService.getCategoryById(Integer.valueOf(sCategoryId));
        return CategoryInProductDto.of(bCategory, mCategory, sCategory);
    }

    @Override
    public CategoryInProductDto resolveCategories(Integer bCategoryId, Integer mCategoryId, Integer sCategoryId) {
        Category bCategory = categoryCrudService.getCategoryById(bCategoryId);
        Category mCategory = categoryCrudService.getCategoryById(mCategoryId);
        Category sCategory = categoryCrudService.getCategoryById(sCategoryId);
        return CategoryInProductDto.of(bCategory, mCategory, sCategory);
    }
}

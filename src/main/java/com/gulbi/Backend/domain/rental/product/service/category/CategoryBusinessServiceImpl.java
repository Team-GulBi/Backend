package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductCategoryUpdateRequestDto;
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
    public CategoryInProductDto resolveCategories(ProductCategoryUpdateRequestDto productCategoryUpdateRequestDto){
        Category bCategory = categoryCrudService.getCategoryById(Integer.valueOf(productCategoryUpdateRequestDto.getBCategoryId()));
        Category mCategory = categoryCrudService.getCategoryById(Integer.valueOf(productCategoryUpdateRequestDto.getMCategoryId()));
        Category sCategory = categoryCrudService.getCategoryById(Integer.valueOf(productCategoryUpdateRequestDto.getSCategoryId()));
        return CategoryInProductDto.of(bCategory, mCategory, sCategory);
    }
}

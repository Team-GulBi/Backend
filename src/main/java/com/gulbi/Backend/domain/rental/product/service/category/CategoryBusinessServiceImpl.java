package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.dto.CategoryDto;
import com.gulbi.Backend.domain.rental.product.dto.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryBusinessServiceImpl implements CategoryBusinessService{
    private final CategoryCrudService categoryCrudService;
    @Override
    public CategoryDto resolveCategories(ProductRegisterRequestDto productRegisterRequestDto) {
        Category bCategory = categoryCrudService.getCategoryById(Integer.valueOf(productRegisterRequestDto.getBcategoryId()));
        Category mCategory = categoryCrudService.getCategoryById(Integer.valueOf(productRegisterRequestDto.getMcategoryId()));
        Category sCategory = categoryCrudService.getCategoryById(Integer.valueOf(productRegisterRequestDto.getScategoryId()));
        return CategoryDto.of(bCategory, mCategory, sCategory);
    }
}

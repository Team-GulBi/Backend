package com.gulbi.Backend.domain.rental.product.service.category;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductRegisterDto;
import com.gulbi.Backend.domain.rental.product.dto.request.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryBusinessServiceImpl implements CategoryBusinessService{
    private final CategoryCrudService categoryCrudService;
    @Override
    public CategoryInProductRegisterDto resolveCategories(ProductRegisterRequestDto productRegisterRequestDto) {
        Category bCategory = categoryCrudService.getCategoryById(Integer.valueOf(productRegisterRequestDto.getBcategoryId()));
        Category mCategory = categoryCrudService.getCategoryById(Integer.valueOf(productRegisterRequestDto.getMcategoryId()));
        Category sCategory = categoryCrudService.getCategoryById(Integer.valueOf(productRegisterRequestDto.getScategoryId()));
        return CategoryInProductRegisterDto.of(bCategory, mCategory, sCategory);
    }
}

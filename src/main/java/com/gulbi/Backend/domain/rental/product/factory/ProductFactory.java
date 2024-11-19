package com.gulbi.Backend.domain.rental.product.factory;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductRegisterDto;
import com.gulbi.Backend.domain.rental.product.dto.request.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.service.category.CategoryBusinessService;
import com.gulbi.Backend.domain.rental.product.service.category.CategoryCrudService;
import com.gulbi.Backend.domain.rental.product.vo.MainImage;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductFactory{
    private final UserService userService;
    private final CategoryBusinessService categoryBusinessService;
    public Product createWithRegisterRequestDto(ProductRegisterRequestDto productRegisterRequestDto) {
        User user = userService.getDummyUser();
        CategoryInProductDto categoryInProductDto = categoryBusinessService.resolveCategories(
                productRegisterRequestDto.getBcategoryId(),
                productRegisterRequestDto.getMcategoryId(),
                productRegisterRequestDto.getScategoryId()
        );
        return Product.builder()
                .user(user)
                .tag(productRegisterRequestDto.getTag())
                .title(productRegisterRequestDto.getTitle())
                .name(productRegisterRequestDto.getName())
                .price(Integer.parseInt(productRegisterRequestDto.getPrice()))
                .sido(productRegisterRequestDto.getSido())
                .sigungu(productRegisterRequestDto.getSigungu())
                .bname(productRegisterRequestDto.getBname())
                .description(productRegisterRequestDto.getDescription())
                .bCategory(categoryInProductDto.getBCategoryId())
                .mCategory(categoryInProductDto.getBCategoryId())
                .sCategory(categoryInProductDto.getSCategoryId())
                .build();

    }
}

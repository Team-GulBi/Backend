package com.gulbi.Backend.domain.rental.product.factory;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductRegisterDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductRegisterDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.vo.MainImage;
import com.gulbi.Backend.domain.user.entity.User;

public class ProductFactory{

    public static Product createProduct(ProductRegisterDto productRegisterDto, CategoryInProductRegisterDto categoryInProductRegisterDto, User user, MainImage mainImage) {
        return Product.builder()
                .user(user)
                .tag(productRegisterDto.getTag())
                .title(productRegisterDto.getTitle())
                .price(Integer.parseInt(productRegisterDto.getPrice()))
                .sido(productRegisterDto.getSido())
                .sigungu(productRegisterDto.getSigungu())
                .bname(productRegisterDto.getBname())
                .description(productRegisterDto.getDescription())
                .bCategory(categoryInProductRegisterDto.getBCategoryId())
                .mCategory(categoryInProductRegisterDto.getMCategoryId())
                .sCategory(categoryInProductRegisterDto.getSCategoryId())
                .mainImage(mainImage.getMainImageUrl())
                .build();

    }
}

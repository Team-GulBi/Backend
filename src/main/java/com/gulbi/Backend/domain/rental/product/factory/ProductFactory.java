package com.gulbi.Backend.domain.rental.product.factory;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductRegisterDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.vo.MainImage;
import com.gulbi.Backend.domain.user.entity.User;

public class ProductFactory{

    public static Product createWithRegisterRequestDto(ProductRegisterDto productRegister) {
        return Product.builder()
                .user(productRegister.getUser())
                .tag(productRegister.getTag())
                .title(productRegister.getTitle())
                .price(Integer.parseInt(productRegister.getPrice()))
                .sido(productRegister.getSido())
                .sigungu(productRegister.getSigungu())
                .bname(productRegister.getBname())
                .description(productRegister.getDescription())
                .bCategory(productRegister.getBCategory())
                .mCategory(productRegister.getMCategory())
                .sCategory(productRegister.getCCategory())
                .build();

    }
}

package com.gulbi.Backend.domain.rental.product.dto.request;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductDto;
import com.gulbi.Backend.domain.user.entity.User;
import lombok.Getter;

@Getter
public class ProductRegisterDto {

    private final String tag;
    private final String title;
    private final String productName;
    private final String price;
    private final String sido;
    private final String sigungu;
    private final String bname;
    private final String description;
    private final CategoryInProductDto categories;
    private final User user;

    private ProductRegisterDto(ProductRegisterRequestDto productRegisterRequest,
                               CategoryInProductDto categoryInProduct,
                               User user) {
        this.tag = productRegisterRequest.getTag();
        this.title = productRegisterRequest.getTitle();
        this.productName = productRegisterRequest.getProductName();
        this.price = productRegisterRequest.getPrice();
        this.sido = productRegisterRequest.getSido();
        this.sigungu = productRegisterRequest.getSigungu();
        this.bname = productRegisterRequest.getBname();
        this.description = productRegisterRequest.getDescription();
        this.categories = categoryInProduct;
        this.user = user;
    }

    public static ProductRegisterDto of(ProductRegisterRequestDto productRegisterRequestDto,
                                        CategoryInProductDto categoryInProduct,
                                        User user) {
        return new ProductRegisterDto(productRegisterRequestDto, categoryInProduct, user);
    }
}

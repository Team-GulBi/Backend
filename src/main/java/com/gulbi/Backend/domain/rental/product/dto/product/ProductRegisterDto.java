package com.gulbi.Backend.domain.rental.product.dto.product;

import com.gulbi.Backend.domain.rental.product.dto.request.ProductRegisterRequestDto;
import lombok.Getter;

@Getter
public class ProductRegisterDto {
    private String tag;
    private String title;
    private String productName;
    private String price;
    private String sido;
    private String sigungu;
    private String bname;
    private String description;

    public ProductRegisterDto(String tag, String title, String productName, String price, String sido, String sigungu, String bname, String description) {
        this.tag = tag;
        this.title = title;
        this.productName = productName;
        this.price = price;
        this.sido = sido;
        this.sigungu = sigungu;
        this.bname = bname;
        this.description = description;
    }

    public static ProductRegisterDto of(ProductRegisterRequestDto product) {
        return new ProductRegisterDto(
                product.getTag(),
                product.getTitle(),
                product.getProductName(),
                product.getPrice(),
                product.getSido(),
                product.getSigungu(),
                product.getBname(),
                product.getDescription()
        );
    }
}
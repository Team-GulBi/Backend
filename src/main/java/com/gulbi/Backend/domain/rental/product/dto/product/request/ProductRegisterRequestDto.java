package com.gulbi.Backend.domain.rental.product.dto.product.request;

import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductRegisterRequestDto {
    private String tag;
    private String title;
    private String name;
    private String price;
    private String sido;
    private String sigungu;
    private String bname;
    private String description;
    private String bcategoryId;
    private String mcategoryId;
    private String scategoryId;
    @Setter
    private ProductImageCollection productImageCollection;
    @Setter
    private ImageUrl mainImage;

    public ProductRegisterRequestDto(String tag, String title, String productName, String price, String sido, String sigungu, String bname, String description, ImageUrl mainImage) {
        this.tag = tag;
        this.title = title;
        this.name = productName;
        this.price = price;
        this.sido = sido;
        this.sigungu = sigungu;
        this.bname = bname;
        this.description = description;
    }
    }


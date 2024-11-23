package com.gulbi.Backend.domain.rental.product.dto.product.request;

import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class ProductRegisterRequestDto {
    private final String tag;
    private final String title;
    private final String name;
    private final String price;
    private final String sido;
    private final String sigungu;
    private final String bname;
    private final String description;
    private final String bcategoryId;
    private final String mcategoryId;
    private final String scategoryId;
    @Setter
    private ProductImageCollection productImageCollection;
    @Setter
    private ImageUrl mainImage;

    public ProductRegisterRequestDto(String tag, String title, String productName, String price, String sido, String sigungu, String bname, String description, ImageUrl mainImage, String bcategoryId, String mcategoryId, String scategoryId) {
        this.tag = tag;
        this.title = title;
        this.name = productName;
        this.price = price;
        this.sido = sido;
        this.sigungu = sigungu;
        this.bname = bname;
        this.description = description;
        this.bcategoryId = bcategoryId;
        this.mcategoryId = mcategoryId;
        this.scategoryId = scategoryId;
    }
    }


package com.gulbi.Backend.domain.rental.product.dto.request;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductRegisterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductRegisterRequestDto {
    private String tag;
    private String title;
    private String productName;
    private String price;
    private String sido;
    private String sigungu;
    private String bname;
    private String description;
    private String bcategoryId;
    private String mcategoryId;
    private String scategoryId;

    public ProductRegisterRequestDto(String tag, String title, String productName, String price, String sido, String sigungu, String bname, String description) {
        this.tag = tag;
        this.title = title;
        this.productName = productName;
        this.price = price;
        this.sido = sido;
        this.sigungu = sigungu;
        this.bname = bname;
        this.description = description;
    }

    public ProductRegisterDto createProductDto() {
        return ProductRegisterDto.of(this);
    }
    }


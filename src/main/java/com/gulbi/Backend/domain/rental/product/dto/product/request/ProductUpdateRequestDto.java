package com.gulbi.Backend.domain.rental.product.dto.product.request;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductDto;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ProductUpdateRequestDto {
    private final Long productId;  // 정확한 필드명 사용
    private final String tag;
    private final String title;
    private final String name;
    private final String price;
    private final String sido;
    private final String sigungu;
    private final String bname;
    private final String description;
    private final ImageUrl mainImage;

    @Setter
    private CategoryInProductDto categoryInProduct;

    public ProductUpdateRequestDto(Long productId, String tag, String title, String productName, String price, String sido, String sigungu, String bname, String description, ImageUrl mainImage) {
        this.productId = productId;
        this.tag = tag;
        this.title = title;
        this.name = productName;
        this.price = price;
        this.sido = sido;
        this.sigungu = sigungu;
        this.bname = bname;
        this.description = description;
        this.mainImage = mainImage;
    }
}

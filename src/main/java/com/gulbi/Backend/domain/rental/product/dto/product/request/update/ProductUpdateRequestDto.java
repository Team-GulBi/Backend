package com.gulbi.Backend.domain.rental.product.dto.product.request.update;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductDto;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
@Getter
public class ProductUpdateRequestDto {
    @Schema(example = "1")
    private final Long productId;
    @Schema(example = "수정1,수정2")
    private final String tag;
    @Schema(example = "제목수정")
    private final String title;
    @Schema(example = "이름수정")
    private final String name;
    @Schema(example = "500000")
    private final Integer price;
    @Schema(example = "시도수정")
    private final String sido;
    @Schema(example = "시군구수정")
    private final String sigungu;
    @Schema(example = "동수정")
    private final String bname;
    @Schema(example = "설명수정")
    private final String description;

    @Hidden
    @Setter
    private  String mainImage;
    @Hidden
    @Setter
    private CategoryInProductDto categoryInProduct;

    public ProductUpdateRequestDto(Long productId, String tag, String title, String productName, Integer price, String sido, String sigungu, String bname, String description) {
        this.productId = productId;
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

package com.gulbi.Backend.domain.rental.product.dto.product.request;

import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRegisterRequestDto {
    @NotNull(message = "1개 이상의 태그를 입력 해 주새요.")
    @Pattern(regexp = "^[\\w가-힣]{1,7}(,[\\w가-힣]{1,7})*$", message = "각 항목은 1자에서 7자 사이의 글자만 포함할 수 있습니다.")
    private String tag;

    @NotNull(message = "제목을 입력 해 주세요.")
    @Size(min = 1, max = 30, message = "최소 1글자에서 최대 30글자 까지 적어주세요.")
    private String title;

    @Size(min = 1, max = 20, message = "최소 1글자에서 최대 20글자 까지 적어주세요.")
    private String name;

    @NotNull(message = "가격을 입력 해 주세요.")
    @Min(value = 1, message = "올바른 가격을 적어주세요.")
    private Integer price;

    private String sido;
    private String sigungu;
    private String bname;

    @NotNull(message = "상품 설명을 입력하세요.")
    @Size(min = 1, max = 1000, message = "최소 1글자에서 최대 1000글자 까지 적어주세요.")
    private String description;

    @NotNull(message = "카테고리 아이디를 넣어주세요")
    @Min(0)
    private String bcategoryId;

    @NotNull(message = "카테고리 아이디를 넣어주세요")
    @Min(0)
    private String mcategoryId;

    @NotNull(message = "카테고리 아이디를 넣어주세요")
    @Min(0)
    private String scategoryId;

    @Setter
    private ProductImageCollection productImageCollection;
    @Setter
    private ImageUrl mainImage;

    // 생성자
    public ProductRegisterRequestDto(String tag, String title, String productName, Integer price, String sido, String sigungu, String bname, String description, ImageUrl mainImage, String bcategoryId, String mcategoryId, String scategoryId) {
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

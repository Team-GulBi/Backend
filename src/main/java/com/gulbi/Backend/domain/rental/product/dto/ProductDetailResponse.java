package com.gulbi.Backend.domain.rental.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ProductDetailResponse {
    private String tag;
    private String title;
    private String productName;
    private String price;
    private String view;

    private String rating;
    private String sido;
    private String sigungu;
    private String bname;
    private String description;

    private String bcategory;
    private String mcategory;
    private String scategory;

    private String created_at;
    private List<String> images;
}

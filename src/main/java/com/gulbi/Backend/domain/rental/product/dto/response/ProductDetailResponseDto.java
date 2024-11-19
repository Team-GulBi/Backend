package com.gulbi.Backend.domain.rental.product.dto.response;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import com.gulbi.Backend.domain.rental.product.dto.ProductImageDtos;
import com.gulbi.Backend.domain.rental.review.dto.ReviewWithAvgProjection;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class ProductDetailResponseDto {
    private final String tag;
    private final String title;
    private final String productName;
    private final String price;
    private final String view;

    private final String rating;
    private final String sido;
    private final String sigungu;
    private final String bname;
    private final String description;

    private final Category bcategory;
    private final Category mcategory;
    private final Category scategory;

    private final LocalDateTime created_at;
    private final List<String> images;

    private final List<ReviewWithAvgProjection> reviews;

    public static ProductDetailResponseDto of(ProductDto product, ProductImageDtos images, List<ReviewWithAvgProjection> reviews) {
        return ProductDetailResponseDto.builder()
                .tag(product.getTag())
                .title(product.getTitle())
                .productName(product.getName())
                .price(String.valueOf(product.getPrice()))
                .view(String.valueOf(product.getViews()))
                .rating(String.valueOf(product.getRating()))
                .sido(product.getSido())
                .sigungu(product.getSigungu())
                .bname(product.getBname())
                .description(product.getDescription())
                .bcategory(product.getBCategory())
                .mcategory(product.getMCategory())
                .scategory(product.getSCategory())
                .created_at(LocalDateTime.now())
                .images(images.toUrlList())
                .reviews(reviews)
                .build();
    }
}

package com.gulbi.Backend.domain.rental.product.dto.product;

import com.gulbi.Backend.domain.rental.product.entity.Category;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductDto {
    private final Long id;
    private final String tag;
    private final String title;
    private final String name;
    private final int views;
    private final int price;
    private final String sido;
    private final String sigungu;
    private final String bname;
    private final String description;
    private final float rating;
    private final Category bCategory;
    private final Category mCategory;
    private final Category sCategory;
    private final LocalDateTime created_at;

    public ProductDto(Long id, String tag, String title, String name, int views, int price, String sido, String sigungu, String bname, String description, float rating, Category bCategory, Category mCategory, Category sCategory, LocalDateTime createdAt) {
        this.id = id;
        this.tag = tag;
        this.title = title;
        this.name = name;
        this.views = views;
        this.price = price;
        this.sido = sido;
        this.sigungu = sigungu;
        this.bname = bname;
        this.description = description;
        this.rating = rating;
        this.bCategory = bCategory;
        this.mCategory = mCategory;
        this.sCategory = sCategory;
        created_at = createdAt;
    }
}


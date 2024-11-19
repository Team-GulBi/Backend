package com.gulbi.Backend.domain.rental.product.vo.image;

import lombok.Getter;

@Getter
public class MainImage {
    private final String mainImageUrl;

    private MainImage(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }
    public static MainImage of(String mainImageUrl){
        return new MainImage(mainImageUrl);
    }
}

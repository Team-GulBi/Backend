package com.gulbi.Backend.domain.rental.product.vo.image;

import lombok.Getter;

@Getter
public class MainImageUrl {
    private final String mainImageUrl;

    private MainImageUrl(String mainImageUrl) {
        this.mainImageUrl = mainImageUrl;
    }
    public static MainImageUrl of(String mainImageUrl){
        return new MainImageUrl(mainImageUrl);
    }
}

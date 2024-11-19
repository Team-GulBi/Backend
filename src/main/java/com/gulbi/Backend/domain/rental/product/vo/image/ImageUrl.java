package com.gulbi.Backend.domain.rental.product.vo.image;

import lombok.Getter;

public class ImageUrl {
    @Getter
    private final String imageUrl;

    private ImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static ImageUrl of(String imageUrl){
        return new ImageUrl(imageUrl);
    }


}

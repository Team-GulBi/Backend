package com.gulbi.Backend.domain.rental.product.vo.image;

import java.util.ArrayList;
import java.util.List;

public class ImageUrls {
    private final List<ImageUrl> imageUrlList;

    private ImageUrls(List<ImageUrl> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }
    public static ImageUrls of(List<ImageUrl> imageUrlList){
        return new ImageUrls(imageUrlList);
    }
    public List<ImageUrl> getImageUrls(){
        return new ArrayList<>(imageUrlList);
    }
}

package com.gulbi.Backend.domain.rental.product.vo.image;

import java.util.ArrayList;
import java.util.List;

public class ImageUrlCollection {
    private final List<ImageUrl> imageUrlList;

    private ImageUrlCollection(List<ImageUrl> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }
    public static ImageUrlCollection of(List<ImageUrl> imageUrlList){
        return new ImageUrlCollection(imageUrlList);
    }
    public List<ImageUrl> getImageUrls(){
        return new ArrayList<>(imageUrlList);
    }
    public ImageUrl getMainImageUrl(){
        return getImageUrls().get(0);
    }
}

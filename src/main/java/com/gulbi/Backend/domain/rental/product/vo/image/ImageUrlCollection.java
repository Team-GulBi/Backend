package com.gulbi.Backend.domain.rental.product.vo.image;

import com.gulbi.Backend.domain.rental.product.code.ProductErrorCode;
import com.gulbi.Backend.domain.rental.product.exception.ImageUrlCollectionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ImageUrlCollection {
    private final List<ImageUrl> imageUrlList;

    private ImageUrlCollection(List<ImageUrl> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }
    public static ImageUrlCollection of(List<ImageUrl> imageUrlList){
        if (imageUrlList.isEmpty()){
            throw new ImageUrlCollectionException.ImageUrlNotFoundException(ProductErrorCode.IMAGEURL_NOT_FOUND);
        }
        return new ImageUrlCollection(imageUrlList);
    }
    public List<ImageUrl> getImageUrls(){
        return new ArrayList<>(imageUrlList);
    }
    public ImageUrl getMainImageUrl() {
        return Optional.ofNullable(getImageUrls().isEmpty() ? null : getImageUrls().get(0))
                .orElseThrow(() -> new ImageUrlCollectionException.ImageUrlNotFoundException(ProductErrorCode.IMAGEURL_NOT_FOUND));
    }



}

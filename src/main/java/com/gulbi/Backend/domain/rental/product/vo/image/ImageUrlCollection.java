package com.gulbi.Backend.domain.rental.product.vo.image;

import com.gulbi.Backend.domain.rental.product.code.ProductErrorCode;
import com.gulbi.Backend.domain.rental.product.exception.ImageVoException;

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
            throw new ImageVoException.ImageUrlNotFoundException(ProductErrorCode.IMAGEURL_NOT_FOUND);
        }
        return new ImageUrlCollection(imageUrlList);
    }
    public List<ImageUrl> getImageUrls(){
        if(!imageUrlList.isEmpty()){
        return new ArrayList<>(imageUrlList);
        }
        return null;
    }
    public ImageUrl getMainImageUrl() {
        return Optional.ofNullable(getImageUrls().isEmpty() ? null : getImageUrls().get(0))
                .orElseThrow(() -> new ImageVoException.ImageUrlNotFoundException(ProductErrorCode.IMAGEURL_NOT_FOUND));
    }

    public ImageUrlCollection append(ImageUrl imageUrl) {
        if(!imageUrlList.isEmpty()){
        List<ImageUrl> newImageUrls = new ArrayList<>(this.imageUrlList);
        newImageUrls.add(imageUrl);
        return new ImageUrlCollection(newImageUrls);
        }
        return null;
    }



}

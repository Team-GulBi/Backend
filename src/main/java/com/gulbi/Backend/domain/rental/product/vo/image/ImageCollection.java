package com.gulbi.Backend.domain.rental.product.vo.image;

import com.gulbi.Backend.domain.rental.product.code.ImageErrorCode;
import com.gulbi.Backend.domain.rental.product.entity.Image;
import com.gulbi.Backend.domain.rental.product.exception.ImageException;
import com.gulbi.Backend.domain.rental.product.exception.ImageVoException;

import java.util.ArrayList;
import java.util.List;

public class ImageCollection {
    private final List<Image> imageList;

    private ImageCollection(List<Image> imageList) {
        this.imageList = imageList;
    }

    public static ImageCollection of(List<Image> imageList){
        return new ImageCollection(imageList);
    }

    public List<Image> getImages(){
        if(imageList.isEmpty()){
            throw  new ImageVoException.ImageCollectionIsEmptyException(ImageErrorCode.IMAGE_COLLECTION_IS_EMPTY);
        }
        return new ArrayList<>(imageList);
    }
}

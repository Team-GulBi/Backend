package com.gulbi.Backend.domain.rental.product.vo.image;

import com.gulbi.Backend.domain.rental.product.entity.Image;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.factory.ImageFactory;

import java.util.ArrayList;
import java.util.List;

public class Images {
    private final List<Image> imageList;

    private Images(List<Image> imageList) {
        this.imageList = imageList;
    }

    public static Images of(List<Image> imageList){
        return new Images(imageList);
    }

    public List<Image> getImages(){
        return new ArrayList<>(imageList);
    }
}

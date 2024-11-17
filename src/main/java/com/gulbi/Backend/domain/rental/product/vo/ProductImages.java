package com.gulbi.Backend.domain.rental.product.vo;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class ProductImages {
    private final List<MultipartFile> productImages;

    private ProductImages(List<MultipartFile> productImages) {
        this.productImages = productImages;
    }

    public static ProductImages of(List<MultipartFile> productImages){
        return new ProductImages(productImages);
    }

    public List<MultipartFile> getProductImages(){
        return new ArrayList<>(productImages);
    }

}

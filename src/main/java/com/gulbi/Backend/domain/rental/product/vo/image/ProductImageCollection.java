package com.gulbi.Backend.domain.rental.product.vo.image;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class ProductImageCollection {
    private final List<MultipartFile> productImages;

    private ProductImageCollection(List<MultipartFile> productImages) {
        this.productImages = productImages;
    }

    public static ProductImageCollection of(List<MultipartFile> productImages){
        return new ProductImageCollection(productImages);
    }

    public List<MultipartFile> getProductImages(){
        return new ArrayList<>(productImages);
    }

    public MultipartFile getProductMainImage(){
        return productImages.get(0);
    }

    public boolean isEmpty(){
        return productImages == null;
    }

}

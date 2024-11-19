package com.gulbi.Backend.domain.rental.product.factory;

import com.gulbi.Backend.domain.rental.product.entity.Image;
import com.gulbi.Backend.domain.rental.product.entity.Product;

public class ImageFactory {
    public static Image createImage(String url, Product product){
        return Image.builder().url(url).product(product).build();
    }
}

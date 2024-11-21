package com.gulbi.Backend.domain.rental.product.factory;

import com.gulbi.Backend.domain.rental.product.entity.Image;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrlCollection;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageCollection;

import java.util.List;
import java.util.stream.Collectors;

public class ImageFactory {
    public static Image createImage(String url, Product product){
        return Image.builder().url(url).product(product).build();
    }
    public static Image createImage(ImageUrl url, Product product){
        return Image.builder().url(url.getImageUrl()).product(product).build();
    }
    public static ImageCollection createImagesFromUrls(ImageUrlCollection imageUrlCollection, Product product) {
        List<ImageUrl> urls = imageUrlCollection.getImageUrls();
        return ImageCollection.of(urls.stream()
                .map(url -> createImage(url, product))
                .collect(Collectors.toList()));
    }
}

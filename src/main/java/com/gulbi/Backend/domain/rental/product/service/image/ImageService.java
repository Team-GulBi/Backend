package com.gulbi.Backend.domain.rental.product.service.image;

import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrlCollection;
import com.gulbi.Backend.domain.rental.product.dto.ProductImageDtos;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageCollection;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;

public interface ImageService {
    public void registerImageWithProduct(ProductImageCollection files, Product product);
    public ImageUrlCollection uploadImagesToS3(ProductImageCollection productImageCollection);
    public ProductImageDtos getImageByProductId(Long productId);
    public void saveImages(ImageCollection imageCollection);
}

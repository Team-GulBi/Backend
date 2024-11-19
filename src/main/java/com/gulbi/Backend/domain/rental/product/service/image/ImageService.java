package com.gulbi.Backend.domain.rental.product.service.image;

import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrls;
import com.gulbi.Backend.domain.rental.product.dto.ProductImageDtos;
import com.gulbi.Backend.domain.rental.product.vo.image.Images;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImages;

public interface ImageService {
    public void registerImageWithProduct(ProductImages files, Product product);
    public ImageUrls uploadImagesToS3(ProductImages productImages);
    public ProductImageDtos getImageByProductId(Long productId);
    public void saveImages(Images images);
}

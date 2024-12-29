package com.gulbi.Backend.domain.rental.product.service.image;

import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageDeleteRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrlCollection;
import com.gulbi.Backend.domain.rental.product.dto.ProductImageDtoCollection;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageCollection;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;

public interface ImageService {
    public void registerImageWithProduct(ImageUrlCollection imageUrlCollection, Product product);
    public ImageUrlCollection uploadImagesToS3(ProductImageCollection productImageCollection);
    public ProductImageDtoCollection getImageByProductId(Long productId);
    public void saveImages(ImageCollection imageCollection);
    public void deleteImages(ProductImageDeleteRequestDto productImageDeleteRequestDto);
}

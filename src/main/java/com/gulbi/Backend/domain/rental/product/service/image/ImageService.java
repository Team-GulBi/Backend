package com.gulbi.Backend.domain.rental.product.service.image;

import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageDeleteRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.register.ProductImageCreateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.update.ProductExistingMainImageUpdateRequestDto;

public interface ImageService {
    public void updateProductImages(ProductImageCreateRequestDto toBeAddedImages,
                                    ProductImageCreateRequestDto toBeUpdatedMainImage,
                                    ProductExistingMainImageUpdateRequestDto toBeUpdateMainImageWithUrl,
                                    ProductImageDeleteRequestDto toBeDeletedImages);

}

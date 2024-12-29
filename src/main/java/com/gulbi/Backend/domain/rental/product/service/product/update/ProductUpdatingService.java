package com.gulbi.Backend.domain.rental.product.service.product.update;

import com.gulbi.Backend.domain.rental.product.dto.product.request.update.ProductCategoryUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.register.ProductImageCreateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageDeleteRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.update.ProductExistingMainImageUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.update.ProductUpdateRequestDto;

public interface ProductUpdatingService {
    public void updateProductViews(Long productId);
    public void updateProductInfo(ProductUpdateRequestDto toBeUpdatedProductInfo,
                                  ProductCategoryUpdateRequestDto toBeUpdatedCategories,
                                  ProductImageCreateRequestDto toBeAddedImages,
                                  ProductImageCreateRequestDto toBeUpdatedMainImage,
                                  ProductExistingMainImageUpdateRequestDto toBeUpdateMainImageWithUrl,
                                  ProductImageDeleteRequestDto toBeDeletedImages);
}

package com.gulbi.Backend.domain.rental.product.service.product.update;

import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductCategoryUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageCreateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageDeleteRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductUpdateRequestDto;

public interface ProductUpdatingService {
    public void updateProductViews(Long productId);
    public void updateProductInfo(ProductUpdateRequestDto productUpdateRequestDto, ProductCategoryUpdateRequestDto productCategoryUpdateRequestDto, ProductImageDeleteRequestDto productImageDeleteRequestDto, ProductImageCreateRequestDto productImageCreateRequestDto);
}

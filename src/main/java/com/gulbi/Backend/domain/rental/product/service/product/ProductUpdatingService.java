package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductCategoryUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageCreateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageDeleteRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;

public interface ProductUpdatingService {
    public void updateProductViews(Long productId);
    public void updateProductInfo(ProductUpdateRequestDto productUpdateRequestDto, ProductCategoryUpdateRequestDto productCategoryUpdateRequestDto, ProductImageDeleteRequestDto productImageDeleteRequestDto, ProductImageCreateRequestDto productImageCreateRequestDto);
}

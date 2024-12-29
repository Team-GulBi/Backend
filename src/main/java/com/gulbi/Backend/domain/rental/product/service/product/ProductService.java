package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.*;
import com.gulbi.Backend.domain.rental.product.dto.product.request.register.ProductImageCreateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.register.ProductMainImageCreateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.register.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.update.ProductCategoryUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.update.ProductExistingMainImageUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.update.ProductUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.response.ProductDetailResponseDto;

import java.util.List;

public interface ProductService {
    public List<ProductOverViewResponse> searchProductOverview(ProductSearchRequestDto productSearchRequestDto);
    public void registrationProduct(ProductRegisterRequestDto productRegisterRequestDto, ProductImageCreateRequestDto productImageCreateRequestDto, ProductMainImageCreateRequestDto productMainImageCreateRequestDto);
    public ProductDetailResponseDto getProductDetail(Long productId);
    public void updateProductViews(Long productId);
    public void updateProduct(ProductUpdateRequestDto toBeUpdatedProductInfo,
                              ProductCategoryUpdateRequestDto toBeUpdatedCategories,
                              ProductImageCreateRequestDto toBeAddedImages,
                              ProductImageCreateRequestDto toBeUpdatedMainImage,
                              ProductExistingMainImageUpdateRequestDto toBeUpdateMainImageWithUrl,
                              ProductImageDeleteRequestDto toBeDeletedImages);
}

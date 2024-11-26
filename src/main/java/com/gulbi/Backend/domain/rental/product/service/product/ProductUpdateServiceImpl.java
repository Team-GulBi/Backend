package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductCategoryUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageCreateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageDeleteRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.service.category.CategoryBusinessService;
import com.gulbi.Backend.domain.rental.product.service.image.ImageService;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductUpdateServiceImpl implements ProductUpdatingService{
    private final ProductCrudService productCrudService;
    private final CategoryBusinessService categoryBusinessService;
    private final ImageService imageService;
    @Override
    public void updateProductViews(Long productId){
        resolveProduct(productId); // 유효성 검사
        productCrudService.updateProductViews(productId);
    }

    @Override
    public void updateProductInfo(ProductUpdateRequestDto productUpdateRequestDto, ProductCategoryUpdateRequestDto productCategoryUpdateRequestDto, ProductImageDeleteRequestDto productImageDeleteRequestDto, ProductImageCreateRequestDto productImageCreateRequestDto){
        Optional.ofNullable(productCategoryUpdateRequestDto)
                .map(categoryBusinessService::resolveCategories)
                .ifPresent(productUpdateRequestDto::setCategoryInProduct);
        Optional.of(productUpdateRequestDto)
                .ifPresent(productCrudService::updateProductInfo);
        Optional.ofNullable(productImageDeleteRequestDto)
                        .ifPresent(imageService::deleteImages);
        Optional.ofNullable(productImageCreateRequestDto.getProductImageCollection())
                .ifPresent(dto ->{
                        productImageCreateRequestDto.setProductId(productUpdateRequestDto.getProductId());
                        imageService.registerImageWithProduct(imageService.uploadImagesToS3(dto), resolveProduct(productImageCreateRequestDto.getProductId()));
                });

    }

    private Product resolveProduct(Long productId){
        return productCrudService.getProductById(productId);
    }

}
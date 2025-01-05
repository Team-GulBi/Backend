package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.*;
import com.gulbi.Backend.domain.rental.product.dto.product.request.register.ProductImageCreateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.register.ProductMainImageCreateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.register.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.response.ProductDetailResponseDto;
import com.gulbi.Backend.domain.rental.product.dto.product.update.ProductImageInfoUpdateDto;
import com.gulbi.Backend.domain.rental.product.dto.product.update.ProductInfoUpdateDto;
import com.gulbi.Backend.domain.rental.product.service.product.register.ProductRegistrationService;
import com.gulbi.Backend.domain.rental.product.service.product.search.ProductSearchService;
import com.gulbi.Backend.domain.rental.product.service.product.update.ProductUpdatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRegistrationService productRegistrationService;
    private final ProductSearchService productSearchService;
    private final ProductUpdatingService productUpdatingService;

    @Override
    public List<ProductOverViewResponse> searchProductOverview(ProductSearchRequestDto productSearchRequestDto) {
        return productSearchService.searchProductByQuery(productSearchRequestDto);
    }
    @Override
    public void registrationProduct(ProductRegisterRequestDto productRegisterRequestDto, ProductImageCreateRequestDto productImageCreateRequestDto, ProductMainImageCreateRequestDto productMainImageCreateRequestDto){
        productRegistrationService.registerProduct(productRegisterRequestDto,productImageCreateRequestDto,productMainImageCreateRequestDto);
    }
    @Override
    public ProductDetailResponseDto getProductDetail(Long productId){
        return productSearchService.getProductDetail(productId);
    }

    @Override
    public void updateProductViews(Long productId) {
        productUpdatingService.updateProductViews(productId);
    }

    @Override
    public void updateProduct(ProductInfoUpdateDto productInfoUpdateDto, ProductImageInfoUpdateDto productImageInfoUpdateDto){
        productUpdatingService.updateProductInfo(productInfoUpdateDto, productImageInfoUpdateDto);
    }

}

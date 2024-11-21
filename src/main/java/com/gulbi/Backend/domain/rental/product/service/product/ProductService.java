package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductSearchRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.response.ProductDetailResponseDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    public List<ProductOverViewResponse> searchProductOverview(ProductSearchRequestDto productSearchRequestDto);
    public void registrationProduct(ProductRegisterRequestDto productRegisterRequestDto);
    public ProductDetailResponseDto getProductDetail(Long productId);


}

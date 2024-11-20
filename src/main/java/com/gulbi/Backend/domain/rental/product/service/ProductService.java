package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductSearchRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.response.ProductDetailResponseDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    public void registerProduct(ProductRegisterRequestDto product) throws IOException;
    public ProductDetailResponseDto getProductDetail(Long productId);
    public List<ProductOverViewResponse> searchProduct(ProductSearchRequestDto productSearchRequestDto);
    public Product createProductWithCategoryAndUser(ProductRegisterRequestDto product);
    public void saveProductAndImages(Product product, ProductRegisterRequestDto productRequest);





}

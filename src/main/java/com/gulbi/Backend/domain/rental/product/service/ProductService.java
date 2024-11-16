package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.ProductDetailResponse;
import com.gulbi.Backend.domain.rental.product.dto.ProductRegisterRequest;
import com.gulbi.Backend.domain.rental.product.dto.ProductResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    public void registerProduct(ProductRegisterRequest product, List<MultipartFile> images) throws IOException;
    public ProductDetailResponse getProductDetail(Long productId);

//    public List<ProductResponseProjection> searchProductWithTitle(String query);
public List<ProductResponseDto> searchProductWithTitle(String query);




}
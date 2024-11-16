package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.response.ProductDetailResponseDto;
import com.gulbi.Backend.domain.rental.product.dto.request.ProductRegisterRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    public void registerProduct(ProductRegisterRequestDto product, List<MultipartFile> images) throws IOException;
    public ProductDetailResponseDto getProductDetail(Long productId);

//    public List<ProductResponseProjection> searchProductWithTitle(String query);
public List<ProductOverViewResponse> searchProductWithTitle(String query);




}

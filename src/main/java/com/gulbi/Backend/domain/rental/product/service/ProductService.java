package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductRegisterDto;
import com.gulbi.Backend.domain.rental.product.dto.response.ProductDetailResponseDto;
import com.gulbi.Backend.domain.rental.product.dto.request.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.user.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    public void registerProduct(ProductRegisterRequestDto product) throws IOException;
    public ProductDetailResponseDto getProductDetail(Long productId);
    public List<ProductOverViewResponse> searchProductWithTitle(String query);
    public Product createProductWithCategoryAndUser(ProductRegisterRequestDto product);
    public void saveProductAndImages(Product product, ProductRegisterRequestDto productRequest);





}

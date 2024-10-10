package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.ProductRegisterRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService2 {
    public void registerProduct(ProductRegisterRequest product, List<MultipartFile> images);




}

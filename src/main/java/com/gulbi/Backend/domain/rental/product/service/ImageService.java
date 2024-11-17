package com.gulbi.Backend.domain.rental.product.service;
import com.gulbi.Backend.domain.rental.product.dto.ProductRegistrationDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    void registerImageWithProduct(List<MultipartFile> files, Product product);
}
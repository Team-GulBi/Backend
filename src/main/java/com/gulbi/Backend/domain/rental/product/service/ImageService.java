package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.vo.ProductImageDtoList;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    public void registerImageWithProduct(List<MultipartFile> files, Product product);
    public ProductImageDtoList getImageByProductId(Long productId);

}

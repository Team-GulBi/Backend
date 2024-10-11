package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.ProductRegisterRequest;
import com.gulbi.Backend.domain.rental.product.entity.Image;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    public void registerImageWithProduct(List<MultipartFile> files, Product product);
    public List<Image> getImageByProductId(Long productId);

}

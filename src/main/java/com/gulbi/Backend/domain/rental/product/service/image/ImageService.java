package com.gulbi.Backend.domain.rental.product.service.image;

import com.gulbi.Backend.domain.rental.product.dto.request.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.vo.ProductImageDtoList;
import com.gulbi.Backend.domain.rental.product.vo.ProductImages;

public interface ImageService {
    public void registerImageWithProduct(ProductRegisterRequestDto files, Product product);
    public ProductImageDtoList getImageByProductId(Long productId);

}

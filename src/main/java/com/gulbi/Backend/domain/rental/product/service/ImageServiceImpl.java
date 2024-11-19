package com.gulbi.Backend.domain.rental.product.service.image;

import com.gulbi.Backend.domain.rental.product.dto.ProductImageDto;
import com.gulbi.Backend.domain.rental.product.dto.request.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Image;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.factory.ImageFactory;
import com.gulbi.Backend.domain.rental.product.repository.ImageRepository;
import com.gulbi.Backend.domain.rental.product.vo.ProductImageDtoList;
import com.gulbi.Backend.domain.rental.product.vo.ProductImages;
import com.gulbi.Backend.global.util.Base64Util;
import com.gulbi.Backend.global.util.FileSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final FileSender fileSender;

    @Override
    public void registerImageWithProduct(ProductRegisterRequestDto files, Product product) {
        ProductImages productImages = files.getProductImages();
        for (MultipartFile file : productImages.getProductImages() ) {
            try {
                String imageUrl = fileSender.sendFile(file);
                Image image = ImageFactory.createImage(imageUrl, product);
                imageRepository.save(image);
            } catch (IOException e) {
                throw new RuntimeException("Error processing file: " + file.getOriginalFilename(), e);
            }
        }

    }


    @Override
    public ProductImageDtoList getImageByProductId(Long productId) {
        List<ProductImageDto> images = imageRepository.findByImageWithProduct(productId);
        return ProductImageDtoList.of(images);

    }


}

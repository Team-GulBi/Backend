package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.ProductImageDto;
import com.gulbi.Backend.domain.rental.product.entity.Image;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.repository.ImageRepository;
import com.gulbi.Backend.domain.rental.product.vo.ProductImageDtoList;
import com.gulbi.Backend.global.util.Base64Util;
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

    @Override
    public void registerImageWithProduct(List<MultipartFile> files, Product product) {
        List<Image> imageList = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                String strBytes = Base64Util.MultipartFileToString(file); // global > util에 만들어놨어용
                Image image = Image.builder().url(strBytes).product(product).build();
                imageList.add(image);
            } catch (IOException e) {
                throw new RuntimeException("Error processing file: " + file.getOriginalFilename(), e);
            }
        }

        // 이미지 리스트를 한 번에 저장
        if (!imageList.isEmpty()) {
            imageRepository.saveAll(imageList);
        }
    }

    @Override
    public ProductImageDtoList getImageByProductId(Long productId) {
        List<ProductImageDto> images = imageRepository.findByImageWithProduct(productId);
        return ProductImageDtoList.of(images);

    }


}

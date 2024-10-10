package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.entity.Image;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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
                byte[] bytes = file.getBytes();
                String strBytes = Base64.getEncoder().encodeToString(bytes);
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
}

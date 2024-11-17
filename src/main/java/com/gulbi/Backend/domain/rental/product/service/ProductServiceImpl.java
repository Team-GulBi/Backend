package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.CategoryDto;
import com.gulbi.Backend.domain.rental.product.dto.ProductRegistrationDto;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ImageService imageService;

    @Override
    public void registerProduct(ProductRegistrationDto productDto, List<MultipartFile> images) {
        // Step 1: 대분류 카테고리 조회
        Category largeCategory = categoryService.getBigCategories().stream()
                .filter(categoryDto -> categoryDto.getId().equals(Long.parseLong(productDto.getBcategoryId())))
                .map(this::convertToCategory)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Large category not found"));

        // Step 2: 중분류 카테고리 조회
        Category mediumCategory = categoryService.getBelowCategoriesByParentId(largeCategory.getId()).stream()
                .filter(categoryDto -> categoryDto.getId().equals(Long.parseLong(productDto.getMcategoryId())))
                .map(this::convertToCategory)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Medium category not found"));

        // Step 3: 소분류 카테고리 조회
        Category smallCategory = categoryService.getBelowCategoriesByParentId(mediumCategory.getId()).stream()
                .filter(categoryDto -> categoryDto.getId().equals(Long.parseLong(productDto.getScategoryId())))
                .map(this::convertToCategory)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Small category not found"));

        // Step 4: Product 엔터티 생성 및 설정
        Product product = Product.builder()
                .name(productDto.getProductName())
                .price(Integer.parseInt(productDto.getPrice()))
                .tag(productDto.getTag())
                .bname(productDto.getBname())
                .sido(productDto.getSido())
                .sigungu(productDto.getSigungu())
                .description(productDto.getDescription())
                .title(productDto.getTitle())
                .bCategory(largeCategory)
                .mCategory(mediumCategory)
                .sCategory(smallCategory)
                .build();

        // Step 5: 상품 정보 저장
        productRepository.save(product);
        imageService.registerImageWithProduct(images,product);
    }

    private Category convertToCategory(CategoryDto categoryDto) {
        return Category.builder()
                .id(categoryDto.getId().intValue())
                .name(categoryDto.getName())
                .build();
    }

    public boolean delteProductById(Long boardId) {
        if (productRepository.existsById(boardId)) {
            productRepository.deleteById(boardId);
        }
    }
}

package com.gulbi.Backend.domain.rental.product.dto.product.request;

import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class ProductImageCreateRequestDto {
    @Valid
    private final ProductImageCollection productImageCollection;
    @Setter
    private Long productId;
    @Setter
    private Product product;

    public ProductImageCreateRequestDto(List<MultipartFile> images) {
        this.productImageCollection = parse(images);
    }

    private ProductImageCollection parse(List<MultipartFile> images) {
        if (images == null || images.isEmpty()) {
            return null;
        }
        return ProductImageCollection.of(images);
    }
}

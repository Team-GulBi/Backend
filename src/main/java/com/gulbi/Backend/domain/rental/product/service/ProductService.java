package com.gulbi.Backend.domain.rental.product.service;
import org.springframework.web.multipart.MultipartFile;
import com.gulbi.Backend.domain.rental.product.dto.ProductRegistrationDto;
import java.util.List;

public interface ProductService {
    void registerProduct(ProductRegistrationDto productDto, List<MultipartFile> images);  // List<MultipartFile>에서 List<String>으로 변경
}

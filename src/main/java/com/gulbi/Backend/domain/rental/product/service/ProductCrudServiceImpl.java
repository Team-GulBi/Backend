package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductCrudServiceImpl implements ProductCrudService{
    private final ProductRepository productRepository;
    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public ProductDto findProductDtoById(Long productId) {
        return productRepository.findProductDtoById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));
    }

    @Override
    public Product findProductById(Long productId) {
        return productRepository.findProductById(productId).orElseThrow();
    }

    @Override
    public List<ProductOverViewResponse> findProductOverViewByQuery(String query) {
        return productRepository.findProductByQuery(query);
    }
}

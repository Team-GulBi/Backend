package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductSearchRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.lang.model.element.NestingKind;
import java.util.Arrays;
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
    public ProductDto getProductDtoById(Long productId) {
        return productRepository.findProductDtoById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findProductById(productId).orElseThrow();
    }

    @Override
    public List<ProductOverViewResponse> getProductOverViewByTitle(String title) {
        return productRepository.findProductsByTitle(title);
    }

    @Override
    public List<ProductOverViewResponse> getProductOverViewByTag(String tag, String tag2, String tag3) {
        return productRepository.findProductsByTag(tag, tag2, tag3);
    }

    @Override
    public void updateProductViews(Long productId){
        productRepository.updateProductViews(productId);
    }

}

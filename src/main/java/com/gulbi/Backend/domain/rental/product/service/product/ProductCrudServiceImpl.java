package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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

    @Override
    public void updateProductInfo(ProductUpdateRequestDto productUpdateRequestDto) {
        Optional.ofNullable(productUpdateRequestDto.getCategoryInProduct())
                .ifPresentOrElse(
                        dto -> productRepository.updateProductInfo(
                                productUpdateRequestDto,
                                dto.getBCategory(),
                                dto.getMCategory(),
                                dto.getSCategory()
                        ),
                        () -> productRepository.updateProductInfo(productUpdateRequestDto)
                );
    }
}



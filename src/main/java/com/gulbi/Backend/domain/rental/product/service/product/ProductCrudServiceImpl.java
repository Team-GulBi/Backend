package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.code.ProductErrorCode;
import com.gulbi.Backend.domain.rental.product.code.ProductSuccessCode;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Category;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.exception.ProductException;
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
                .orElseThrow(
                        () -> new ProductException.ProductNotFoundException(ProductErrorCode.PRODUCT_NOT_FOUND));
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findProductById(productId)
                .orElseThrow(
                     () -> new ProductException.ProductNotFoundException(ProductErrorCode.PRODUCT_NOT_FOUND)
        );
    }

    @Override
    public List<ProductOverViewResponse> getProductOverViewByTitle(String title) {
        return Optional.ofNullable(productRepository.findProductsByTitle(title))
                .orElseThrow(
                        ()-> new ProductException.NoProductFoundForTitleException(ProductErrorCode.PRODUCT_NOT_FOUND_BY_TITLE));
    }

    @Override
    public List<ProductOverViewResponse> getProductOverViewByTag(String tag, String tag2, String tag3) {
        return Optional.ofNullable(productRepository.findProductsByTag(tag,tag2,tag3))
                .orElseThrow(
                        ()-> new ProductException.NoProductFoundForTitleException(ProductErrorCode.PRODUCT_NOT_FOUND_BY_TAGS));
    }

    @Override
    public void updateProductViews(Long productId){
        Optional.ofNullable(productRepository.updateProductViews(productId))
                .filter(updatedRows -> updatedRows > 0)
                .orElseThrow(
                        () -> new ProductException.NoUpdateProductException(ProductErrorCode.NO_UPDATED_COLUMNS)
                );
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



package com.gulbi.Backend.domain.rental.product.service.product.crud;

import com.gulbi.Backend.domain.rental.product.code.ProductErrorCode;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.update.ProductUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.exception.ProductException;
import com.gulbi.Backend.domain.rental.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import jakarta.persistence.PersistenceException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCrudServiceImpl implements ProductCrudService{
    private final ProductRepository productRepository;
    @Override
    public void saveProduct(Product product) {
        try {
            productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            throw new ProductException.ProductValidationException(ProductErrorCode.PRODUCT_VALIDATION_FAILED);
        } catch (JpaSystemException | PersistenceException e) {
            throw new ProductException.DatabaseErrorException(ProductErrorCode.DATABASE_ERROR);
        } catch (IllegalArgumentException e) {
            throw new ProductException.MissingProductFieldException(ProductErrorCode.MISSING_REQUIRED_FIELD);
        }
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
        List<ProductOverViewResponse> overViewResponses = productRepository.findProductsByTitle(title);
        if(overViewResponses.isEmpty()){
            throw new ProductException.NoProductFoundForTitleException(ProductErrorCode.PRODUCT_NOT_FOUND_BY_TITLE);
        }
        return overViewResponses;

    }

    @Override
    public List<ProductOverViewResponse> getProductOverViewByTag(String tag, String tag2, String tag3) {
        List<ProductOverViewResponse> overViewResponses =  productRepository.findProductsByTag(tag,tag2,tag3);
        if(overViewResponses.isEmpty()){
            throw new ProductException.NoProductFoundForTagsException(ProductErrorCode.PRODUCT_NOT_FOUND_BY_TAGS);
        }
        return overViewResponses;
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



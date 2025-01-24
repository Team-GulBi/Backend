package com.gulbi.Backend.domain.rental.product.service.product.crud;

import com.gulbi.Backend.domain.rental.product.code.ProductErrorCode;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.update.ProductUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.update.ProductMainImageUpdateDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.exception.ProductException;
import com.gulbi.Backend.domain.rental.product.repository.ProductRepository;
import com.gulbi.Backend.global.error.ExceptionMetaData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import jakarta.persistence.PersistenceException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductCrudServiceImpl implements ProductCrudService {
    private final ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        try {
            productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            throw createProductValidationException(product);
        } catch (JpaSystemException | PersistenceException e) {
            throw createDatabaseErrorException(product);
        } catch (IllegalArgumentException e) {
            throw createMissingProductFieldException(product);
        }
    }

    @Override
    public ProductDto getProductDtoById(Long productId) {
        return productRepository.findProductDtoById(productId)
                .orElseThrow(() -> createProductNotFoundException(productId));
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findProductById(productId)
                .orElseThrow(() -> createProductNotFoundExceptionWithMetaData(productId));
    }

    @Override
    public List<ProductOverViewResponse> getProductOverViewByTitle(String title) {
        List<ProductOverViewResponse> overViewResponses = productRepository.findProductsByTitle(title);
        if (overViewResponses.isEmpty()) {
            throw createNoProductFoundForTitleException(title);
        }
        return overViewResponses;
    }

    @Override
    public List<ProductOverViewResponse> getProductOverViewByTag(String tag, String tag2, String tag3) {
        List<ProductOverViewResponse> overViewResponses = productRepository.findProductsByTag(tag, tag2, tag3);
        if (overViewResponses.isEmpty()) {
            throw createNoProductFoundForTagsException(tag, tag2, tag3);
        }
        return overViewResponses;
    }

    @Override
    public void updateProductViews(Long productId) {
        Optional.ofNullable(productRepository.updateProductViews(productId))
                .filter(updatedRows -> updatedRows > 0)
                .orElseThrow(() -> createNoUpdateProductException(productId));
    }

    @Override
    public void updateProductInfo(ProductUpdateRequestDto dto) {
        productRepository.updateProductInfo(
                dto.getProductId(),
                dto.getTag(),
                dto.getTitle(),
                dto.getName(),
                dto.getPrice(),
                dto.getSido(),
                dto.getSigungu(),
                dto.getBname(),
                dto.getDescription()
        );

        if (dto.getCategoryInProduct() != null) {
            productRepository.updateProductCategories(dto.getProductId(),
                    dto.getCategoryInProduct().getBCategory(),
                    dto.getCategoryInProduct().getMCategory(),
                    dto.getCategoryInProduct().getSCategory());
        }
    }

    @Override
    public void updateProductMainImage(ProductMainImageUpdateDto productMainImageUpdateDto) {
        productRepository.updateProductMainImage(
                productMainImageUpdateDto.getMainImageUrl().getImageUrl(),
                productMainImageUpdateDto.getProductId()
        );
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteAllbyId(productId);
    }

    private ProductException.ProductValidationException createProductValidationException(Product product) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(product, this.getClass().getName());
        return new ProductException.ProductValidationException(ProductErrorCode.PRODUCT_VALIDATION_FAILED, exceptionMetaData);
    }

    private ProductException.DatabaseErrorException createDatabaseErrorException(Product product) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(product, this.getClass().getName());
        return new ProductException.DatabaseErrorException(ProductErrorCode.DATABASE_ERROR, exceptionMetaData);
    }

    private ProductException.MissingProductFieldException createMissingProductFieldException(Product product) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(product, this.getClass().getName());
        return new ProductException.MissingProductFieldException(ProductErrorCode.MISSING_REQUIRED_FIELD, exceptionMetaData);
    }

    private ProductException.ProductNotFoundException createProductNotFoundException(Long productId) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(productId, this.getClass().getName());
        return new ProductException.ProductNotFoundException(ProductErrorCode.PRODUCT_NOT_FOUND, exceptionMetaData);
    }

    private ProductException.ProductNotFoundException createProductNotFoundExceptionWithMetaData(Long productId) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(productId, this.getClass().getName());
        return new ProductException.ProductNotFoundException(ProductErrorCode.PRODUCT_NOT_FOUND, exceptionMetaData);
    }

    private ProductException.NoProductFoundForTitleException createNoProductFoundForTitleException(String title) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(title, this.getClass().getName());
        return new ProductException.NoProductFoundForTitleException(ProductErrorCode.PRODUCT_NOT_FOUND_BY_TITLE, exceptionMetaData);
    }

    private ProductException.NoProductFoundForTagsException createNoProductFoundForTagsException(String tag, String tag2, String tag3) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(tag + ", " + tag2 + ", " + tag3, this.getClass().getName());
        return new ProductException.NoProductFoundForTagsException(ProductErrorCode.PRODUCT_NOT_FOUND_BY_TAGS, exceptionMetaData);
    }

    private ProductException.NoUpdateProductException createNoUpdateProductException(Long productId) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(productId, this.getClass().getName());
        return new ProductException.NoUpdateProductException(ProductErrorCode.NO_UPDATED_COLUMNS, exceptionMetaData);
    }
}

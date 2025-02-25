package com.gulbi.Backend.domain.rental.product.service.product.crud;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.update.ProductUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.update.ProductMainImageUpdateDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;

import java.util.List;

public interface ProductCrudService {
    public Long saveProduct(Product product);
    public ProductDto getProductDtoById(Long productId);
    public Product getProductById(Long productId);
    public List<ProductOverViewResponse> getProductOverViewByTag(String tag, String tag2, String tag3);
    public List<ProductOverViewResponse> getProductOverViewByTitle(String title);
    public List<ProductOverViewResponse> getProductOverViewByproductIds(List<Long> productIds);
    public List<ProductOverViewResponse> getProductOverViewByCreatedAtDesc();
    public void updateProductViews(Long productId);
    public void updateProductInfo(ProductUpdateRequestDto productUpdateRequestDto);
    public void updateProductMainImage(ProductMainImageUpdateDto productMainImageUpdateDto);
    public void deleteProduct(Long productId);

}

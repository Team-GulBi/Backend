package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.entity.Product;

import java.util.List;

public interface ProductCrudService {
    public void saveProduct(Product product);
    public ProductDto findProductDtoById(Long productId);
    public Product findProductById(Long productId);
    public List<ProductOverViewResponse> findProductOverViewByQuery(String query);
}

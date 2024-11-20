package com.gulbi.Backend.domain.rental.product.service.product.strategy;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductSearchRequestDto;
import com.gulbi.Backend.domain.rental.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service("태그")
@RequiredArgsConstructor
public class ProductSearchByTagStrategy implements ProductSearchStrategy {

    private final ProductRepository productRepository;

    @Override
    public List<ProductOverViewResponse> search(String query) {
        List<String> queries = Arrays.stream(query.split(",")).toList();
        return productRepository.findProductsByTag(queries.get(0),queries.get(1),queries.get(2));
    }
}

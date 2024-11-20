package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductSearchRequestDto;
import com.gulbi.Backend.domain.rental.product.service.product.strategy.ProductSearchByTagStrategy;
import com.gulbi.Backend.domain.rental.product.service.product.strategy.ProductSearchByTitleStrategy;
import com.gulbi.Backend.domain.rental.product.service.product.strategy.ProductSearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;
import java.util.Optional;

@Service
public class ProductSearchServiceImpl implements ProductSearchService {

    private final Map<String, ProductSearchStrategy> productSearchStrategies;
    @Autowired
    public ProductSearchServiceImpl(Map<String, ProductSearchStrategy> productSearchStrategies) {
        this.productSearchStrategies = productSearchStrategies;
    }

    @Override
    public List<ProductOverViewResponse> searchProductByQuery(ProductSearchRequestDto productSearchRequestDto) {
        String detail = productSearchRequestDto.getDetail().trim();
        String query = productSearchRequestDto.getQuery();
        System.out.println("Map에 등록된 전략들: " + productSearchStrategies.keySet());
        System.out.println("들어온 전략 key" + detail);
        ProductSearchStrategy productSearchStrategy = productSearchStrategies.get(detail);

        return productSearchStrategy.search(query);
    }
}

package com.gulbi.Backend.domain.rental.recommandation.service;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.service.product.crud.ProductCrudService;
import com.gulbi.Backend.domain.rental.product.service.product.logging.ProductLogHandler;
import com.gulbi.Backend.domain.rental.recommandation.repository.ProductLogQueryService;
import com.gulbi.Backend.domain.rental.recommandation.repository.QueryHandler;
import com.gulbi.Backend.domain.rental.recommandation.vo.ExtractedProductIds;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRecommandServiceImpl implements ProductRecommandService {
    private final ProductLogQueryService productLogQueryService;
    private final ProductCrudService productCrudService;
    private final QueryHandler queryHandler;

    public ProductRecommandServiceImpl(ProductLogQueryService productLogQueryService, ProductCrudService productCrudService, QueryHandler queryHandler) {
        this.productLogQueryService = productLogQueryService;
        this.productCrudService = productCrudService;
        this.queryHandler = queryHandler;
    }

    @Override
    public List<ProductOverViewResponse> getRealTimePopularProducts() {
        String response = productLogQueryService.getQueryOfPopularProductIds();
        List<Long> productIds = queryHandler.getListOfProductIds(response).getProductIds();
        return productCrudService.getProductOverViewByproductIds(productIds);

    }
}

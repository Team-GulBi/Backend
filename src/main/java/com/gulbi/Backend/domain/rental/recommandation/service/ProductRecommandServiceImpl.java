package com.gulbi.Backend.domain.rental.recommandation.service;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.service.product.crud.ProductCrudService;
import com.gulbi.Backend.domain.rental.recommandation.vo.ExtractedRecommendation;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductRecommandServiceImpl implements ProductRecommandService {
    private final ProductLogQueryService productLogQueryService;
    private final ProductCrudService productCrudService;
    private final QueryHandler queryHandler;
    private final RecommendatedProviderService recommendatedProviderService;

    public ProductRecommandServiceImpl(ProductLogQueryService productLogQueryService, ProductCrudService productCrudService, QueryHandler queryHandler, RecommendatedProviderService recommendatedProviderService) {
        this.productLogQueryService = productLogQueryService;
        this.productCrudService = productCrudService;
        this.queryHandler = queryHandler;
        this.recommendatedProviderService = recommendatedProviderService;
    }

    @Override
    public List<ProductOverViewResponse> getRealTimePopularProducts() {
        String response = productLogQueryService.getQueryOfPopularProductIds();
        List<Long> productIds = queryHandler.getListOfProductIds(response).getProductIds();
        return productCrudService.getProductOverViewByproductIds(productIds);

    }

    @Override
    public List<ProductOverViewResponse> getRecentRegistrationProducts(Pageable pageable, LocalDateTime lastCreatedAt) {
        return productCrudService.getProductOverViewByCreatedAtDesc(pageable,lastCreatedAt);
    }

    @Override
    public List<ProductOverViewResponse> getRecentProductByCategory(Long bCategoryId, Long mCategoryId, Long sCategoryId, LocalDateTime lastCreatedAt, Pageable pageable) {
        return productCrudService.getProductOverViewByCategories(bCategoryId, mCategoryId, sCategoryId,lastCreatedAt,pageable);
    }

    @Override
    public List<ProductOverViewResponse> getPersonalizedRecommendationProducts() {
        String result = productLogQueryService.getQueryOfMostViewedCategoriesByUser();
        ExtractedRecommendation extractedRecommendation = queryHandler.getMapOfRecommandation(result);
        extractedRecommendation.printRecommendationIndices();
        recommendatedProviderService.getRecommendatedProducts(extractedRecommendation);


        return List.of();
    }
}

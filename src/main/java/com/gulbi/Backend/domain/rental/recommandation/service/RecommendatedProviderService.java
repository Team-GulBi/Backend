package com.gulbi.Backend.domain.rental.recommandation.service;

import com.gulbi.Backend.domain.rental.product.service.product.search.strategy.search.ProductSearchStrategy;
import com.gulbi.Backend.domain.rental.recommandation.service.strategy.RecommendStrategyService;
import com.gulbi.Backend.domain.rental.recommandation.vo.ExtractedRecommendation;
import com.gulbi.Backend.domain.rental.recommandation.vo.RecommendationProducts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecommendatedProviderService implements RecommendatedProvider{
    private final Map<String,RecommendStrategyService> recommendStrategyServiceMap;

    @Autowired
    public RecommendatedProviderService(Map<String,RecommendStrategyService> recommendStrategyServiceMap, Map<String, ProductSearchStrategy> productSearchStrategies) {
        this.recommendStrategyServiceMap = recommendStrategyServiceMap;
    }


    @Override
    public RecommendationProducts getRecommendatedProducts(ExtractedRecommendation extractedRecommendation) {
        recommendStrategyServiceMap.get("3").getRecommendatedProducts(extractedRecommendation);
        return null;
    }


}

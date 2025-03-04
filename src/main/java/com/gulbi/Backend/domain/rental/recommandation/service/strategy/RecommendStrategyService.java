package com.gulbi.Backend.domain.rental.recommandation.service.strategy;

import com.gulbi.Backend.domain.rental.recommandation.vo.ExtractedRecommendation;
import com.gulbi.Backend.domain.rental.recommandation.vo.RecommendationProducts;

public interface RecommendStrategyService {

    RecommendationProducts getRecommendatedProducts(ExtractedRecommendation extractedRecommendation);
}

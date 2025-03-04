package com.gulbi.Backend.domain.rental.recommandation.service;

import com.gulbi.Backend.domain.rental.recommandation.vo.ExtractedRecommendation;
import com.gulbi.Backend.domain.rental.recommandation.vo.RecommendationProducts;

public interface RecommendatedProvider {

    RecommendationProducts getRecommendatedProducts(ExtractedRecommendation extractedRecommendation);
}

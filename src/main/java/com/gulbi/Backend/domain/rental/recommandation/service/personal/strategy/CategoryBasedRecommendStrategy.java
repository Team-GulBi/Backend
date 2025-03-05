package com.gulbi.Backend.domain.rental.recommandation.service.personal.strategy;

import com.gulbi.Backend.domain.rental.recommandation.vo.ExtractedRecommendation;
import com.gulbi.Backend.domain.rental.recommandation.vo.PersonalRecommendationRequestDto;
import com.gulbi.Backend.domain.rental.recommandation.vo.PersonalRecommendationResponseDto;

public interface CategoryBasedRecommendStrategy {

    PersonalRecommendationResponseDto getRecommendatedProducts(ExtractedRecommendation extractedRecommendation);
    PersonalRecommendationResponseDto getRecommendatedProducts(PersonalRecommendationRequestDto personalRecommendationRequestDto);
}

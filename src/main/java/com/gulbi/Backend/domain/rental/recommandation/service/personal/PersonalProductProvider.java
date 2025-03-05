package com.gulbi.Backend.domain.rental.recommandation.service.personal;

import com.gulbi.Backend.domain.rental.recommandation.vo.PersonalRecommendationRequestDto;
import com.gulbi.Backend.domain.rental.recommandation.vo.PersonalRecommendationResponseDto;

public interface PersonalProductProvider {
    PersonalRecommendationResponseDto getPersonalizedRecommendationProducts(PersonalRecommendationRequestDto personalRecommendationRequestDto);
}

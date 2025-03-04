package com.gulbi.Backend.domain.rental.recommandation.service;

import com.gulbi.Backend.domain.user.entity.User;

public interface ProductLogQueryService {

    String getQueryOfPopularProductIds();
    String getQueryOfMostViewedCategoriesByUser();
}

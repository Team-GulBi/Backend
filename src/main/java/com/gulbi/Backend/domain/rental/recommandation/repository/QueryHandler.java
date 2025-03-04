package com.gulbi.Backend.domain.rental.recommandation.repository;

import com.gulbi.Backend.domain.rental.recommandation.vo.ExtractedProductIds;

public interface QueryHandler {

    ExtractedProductIds getListOfProductIds(String queryResult);
}

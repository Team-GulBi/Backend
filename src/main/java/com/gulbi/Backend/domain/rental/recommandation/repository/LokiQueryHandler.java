package com.gulbi.Backend.domain.rental.recommandation.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gulbi.Backend.domain.rental.recommandation.vo.ExtractedProductIds;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class LokiQueryHandler implements QueryHandler{
    @Override
    public ExtractedProductIds getListOfProductIds(String queryResult) {
        return ExtractedProductIds.of(JsonPath.read(queryResult, "$.data.result[*].metric.productId"));
    }
}

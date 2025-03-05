package com.gulbi.Backend.domain.rental.recommandation.service.query;

import com.gulbi.Backend.domain.rental.recommandation.vo.ExtractedProductIds;
import com.gulbi.Backend.domain.rental.recommandation.vo.ExtractedRecommendation;
import com.jayway.jsonpath.JsonPath;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LokiQueryHandler implements QueryHandler{
    @Override
    public ExtractedProductIds getListOfProductIds(String queryResult) {
        return ExtractedProductIds.of(JsonPath.read(queryResult, "$.data.result[*].metric.productId"));
    }

    @Override
    public ExtractedRecommendation getMapOfRecommandation(String queryResult) {
        // 실패작들
        //        System.out.println(Optional.ofNullable(JsonPath.read(queryResult, "$.data.result[*].value[*].values")));
        //        System.out.println(Optional.ofNullable(JsonPath.read(queryResult, "$.data.result[*].metric[*].bCategoryId")));
        System.out.println(queryResult);
        System.out.println(Optional.ofNullable(JsonPath.read(queryResult, "$.data.result[*].metric.bCategoryId")));
        System.out.println(Optional.ofNullable(JsonPath.read(queryResult, "$.data.result[*].metric.mCategoryId")));
        System.out.println(Optional.ofNullable(JsonPath.read(queryResult, "$.data.result[*].value[1]")));
        List<String> bCategoryList = JsonPath.read(queryResult, "$.data.result[*].metric.bCategoryId");
        List<String> mCategoryList =JsonPath.read(queryResult, "$.data.result[*].metric.mCategoryId");
        List<String> priorityList =JsonPath.read(queryResult, "$.data.result[*].value[1]");

        return ExtractedRecommendation.of(bCategoryList, mCategoryList, priorityList);
    }

}

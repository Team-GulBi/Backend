package com.gulbi.Backend.domain.rental.recommandation.service;

import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LokiProductLogQueryService implements ProductLogQueryService {

    private final WebClient webClient = WebClient.create("http://localhost:3100");
    private final UserService userService;

    public LokiProductLogQueryService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getQueryOfPopularProductIds() {
        String query = "topk(3, sum(count_over_time({job=\"popularProduct\"} | json | line_format \"{{.productId}}\" [600m])) by (productId))";
        String rawLokiQuery = String.format(query);
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/loki/api/v1/query")
                        .queryParam("query", "{query}")
                        .build(query))
                .retrieve()
                .bodyToMono(String.class)
                .doOnTerminate(() -> System.out.println("Query execution finished"))
                .block();
        return response;
    }


    @Override
    public String getQueryOfMostViewedCategoriesByUser() {
//        String query = String.format(
//                "topk(3, sum(count_over_time({job=\"personalRecommandationProduct\"} \n" +
//                        "    | json \n" +
//                        "    | metadata_userId == %d\n" +
//                        "    | line_format \"{{.bCategoryId}},{{.mCategoryId}}\" [1000m])) \n" +
//                        "    by (bCategoryId, mCategoryId))", getAuthenticationUser().getId());
        String query =
                "topk(3, sum(count_over_time({job=\"personalRecommandationProduct\"} \n" +
                        "    | json \n" +
                        "    | metadata_userId == 51\n" +
                        "    | line_format \"{{.bCategoryId}},{{.mCategoryId}}\" [1000m])) \n" +
                        "    by (bCategoryId, mCategoryId))";
        String response = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/loki/api/v1/query")
                        .queryParam("query", "{query}")
                        .build(query))
                .retrieve()
                .bodyToMono(String.class)
                .doOnTerminate(() -> System.out.println("Query execution finished"))
                .block();
        return response;
    }

    private User getAuthenticationUser(){
        return userService.getAuthenticatedUser();
    }
}
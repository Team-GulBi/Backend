package com.gulbi.Backend.domain.rental.recommandation.repository;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class LokiProductLogQueryService implements ProductLogQueryService {

    private final WebClient webClient = WebClient.create("http://localhost:3100");


    @Override
    public String getQueryOfPopularProductIds() {
        String query = "topk(3, sum(count_over_time({job=\"product\"} | json | line_format \"{{.productId}}\" [600m])) by (productId))";
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
}
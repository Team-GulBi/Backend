package com.gulbi.Backend.domain.rental.product.service.product.logging;

public interface ProductLogHandler {
    void loggingQueryData(String query, String detail);
    void loggingProductIdData(Long productId);
}

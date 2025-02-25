package com.gulbi.Backend.domain.rental.product.service.product.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

@Service
public class LokiLoggingHandler implements ProductLogHandler{
    private static final Logger logger = LoggerFactory.getLogger(LokiLoggingHandler.class);
    @Override
    public void loggingQueryData(String query, String detail) {
        MDC.put("query", query);
        MDC.put("detail", detail);
        logger.info("Queried Product Information");
        MDC.clear();
    }

    @Override
    public void loggingProductIdData(Long productId) {
        MDC.put("productId", String.valueOf(productId));
        logger.info("Queried ProductId Information");
        MDC.clear();
    }
}

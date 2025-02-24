package com.gulbi.Backend.domain.rental.recommandation.controller;

import com.gulbi.Backend.domain.rental.recommandation.service.ProductRecommandService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recommand")
public class ProductRecommandController {
    private final ProductRecommandService productRecommandService;

    public ProductRecommandController(ProductRecommandService productRecommandService) {
        this.productRecommandService = productRecommandService;
    }

    @GetMapping
    public void showRealTimePopularProduct(){
        productRecommandService.getRealTimePopularProducts();
    }
}

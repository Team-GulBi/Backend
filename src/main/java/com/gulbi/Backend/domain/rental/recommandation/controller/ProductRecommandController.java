package com.gulbi.Backend.domain.rental.recommandation.controller;

import com.gulbi.Backend.domain.rental.product.code.ProductSuccessCode;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.recommandation.code.RecommandationSuccessCode;
import com.gulbi.Backend.domain.rental.recommandation.service.ProductRecommandService;
import com.gulbi.Backend.global.response.ResponseApiCode;
import com.gulbi.Backend.global.response.RestApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recommand")
public class ProductRecommandController {
    private final ProductRecommandService productRecommandService;

    public ProductRecommandController(ProductRecommandService productRecommandService) {
        this.productRecommandService = productRecommandService;
    }

    @GetMapping
    public ResponseEntity<RestApiResponse> showRealTimePopularProduct(){
        List<ProductOverViewResponse> products = productRecommandService.getRealTimePopularProducts();
        RestApiResponse response = new RestApiResponse(RecommandationSuccessCode.REALTIME_POPULAR_PRODUCTS_FOUND_SUCCESS,products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/recent")
    public ResponseEntity<RestApiResponse> showRecentRegistratedProducts(){
        List<ProductOverViewResponse> products = productRecommandService.getRecentRegistrationProducts();
        RestApiResponse response = new RestApiResponse(RecommandationSuccessCode.REALTIME_POPULAR_PRODUCTS_FOUND_SUCCESS,products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category")
    public ResponseEntity<RestApiResponse> showRecentProductByCategory(@Parameter(description = "대분류", required = true) @RequestParam("bCategoryId") Long bCategoryId,
                                                                 @Parameter(description = "중분류", required = false) @RequestParam("mCategoryId") Long mCategoryId,
                                                                 @Parameter(description = "소분류", required = false) @RequestParam("sCategoryId") Long sbCategoryId
                                                                 ){
        List<ProductOverViewResponse> products = productRecommandService.getRecentProductByCategory(bCategoryId, mCategoryId, sbCategoryId);
        RestApiResponse response = new RestApiResponse(RecommandationSuccessCode.REALTIME_POPULAR_PRODUCTS_FOUND_SUCCESS,products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("personality")
    public ResponseEntity<RestApiResponse> showPersonalRecommandationProducts(){
        List<ProductOverViewResponse> products = productRecommandService.getPersonalizedRecommendationProducts();
        RestApiResponse response = new RestApiResponse(RecommandationSuccessCode.REALTIME_POPULAR_PRODUCTS_FOUND_SUCCESS,products);
        return ResponseEntity.ok(response);
    }
    }


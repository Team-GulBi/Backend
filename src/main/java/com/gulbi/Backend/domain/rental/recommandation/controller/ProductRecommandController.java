package com.gulbi.Backend.domain.rental.recommandation.controller;

import com.gulbi.Backend.domain.rental.product.code.ProductSuccessCode;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.recommandation.code.RecommandationSuccessCode;
import com.gulbi.Backend.domain.rental.recommandation.service.ProductRecommandService;
import com.gulbi.Backend.domain.rental.recommandation.vo.PersonalRecommendationRequestDto;
import com.gulbi.Backend.global.response.ResponseApiCode;
import com.gulbi.Backend.global.response.RestApiResponse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<RestApiResponse> showRecentRegistratedProducts(@Parameter(description = "lastCreatedAt",required = false)@RequestParam(value = "lastCreatedAt", required = false) LocalDateTime lastCreatedAt,
                                                                         @Parameter(description = "size", required = true) @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(0, size);
        List<ProductOverViewResponse> products = productRecommandService.getRecentRegistrationProducts(pageable,lastCreatedAt);
        RestApiResponse response = new RestApiResponse(RecommandationSuccessCode.REALTIME_POPULAR_PRODUCTS_FOUND_SUCCESS,products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category")
    public ResponseEntity<RestApiResponse> showRecentProductByCategory(
            @Parameter(description = "대분류", required = true) @RequestParam("bCategoryId") Long bCategoryId,
            @Parameter(description = "중분류", required = false) @RequestParam("mCategoryId") Long mCategoryId,
            @Parameter(description = "소분류", required = false) @RequestParam("sCategoryId") Long sCategoryId,
            @Parameter(description = "lastCreatedAt", required = false) @RequestParam(value = "lastCreatedAt", required = false) LocalDateTime lastCreatedAt,
            @Parameter(description = "size", required = true) @RequestParam("size") int size
    ) {
        Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        List<ProductOverViewResponse> products = productRecommandService.getRecentProductByCategory(bCategoryId, mCategoryId,sCategoryId,lastCreatedAt,pageable);
        RestApiResponse response = new RestApiResponse(RecommandationSuccessCode.REALTIME_POPULAR_PRODUCTS_FOUND_SUCCESS,products);
        return ResponseEntity.ok(response);
    }

    @GetMapping("personality")
    public ResponseEntity<RestApiResponse> showPersonalRecommendationProducts(){
        List<ProductOverViewResponse> products = productRecommandService.getPersonalizedRecommendationProducts();
        RestApiResponse response = new RestApiResponse(RecommandationSuccessCode.REALTIME_POPULAR_PRODUCTS_FOUND_SUCCESS,products);
        return ResponseEntity.ok(response);
    }
    }


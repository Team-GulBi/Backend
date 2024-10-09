package com.gulbi.Backend.domain.rental.product.controller;

import com.gulbi.Backend.domain.rental.product.code.ProductSuccessCode;
import com.gulbi.Backend.domain.rental.product.dto.ProductRegisterRequest;
import com.gulbi.Backend.domain.rental.product.service.ProductService2;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController2 {

    private final ProductService2 productService;

    @PostMapping
    public ResponseEntity<RestApiResponse> register(@RequestBody ProductRegisterRequest product){
        // user는 임의로 하나 만들어서 넣고 사용하겠음.
        // jwt를 통해 인증을 받아야 하는 로직 확인 후 수정 예정임
        productService.registerProduct(product);
        RestApiResponse response = new RestApiResponse(ProductSuccessCode.PRODUCT_REGISTER_SUCCESS);
        return ResponseEntity.ok(response);
    }
}

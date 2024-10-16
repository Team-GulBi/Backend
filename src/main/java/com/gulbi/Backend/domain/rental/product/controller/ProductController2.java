package com.gulbi.Backend.domain.rental.product.controller;

import com.gulbi.Backend.domain.rental.product.code.ProductSuccessCode;
import com.gulbi.Backend.domain.rental.product.dto.ProductRegisterRequest;
import com.gulbi.Backend.domain.rental.product.service.ImageService;
import com.gulbi.Backend.domain.rental.product.service.ProductService2;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.global.response.RestApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController2 {

    private final ProductService2 productService;
    private final ImageService imageService;


    @CrossOrigin("http://localhost:5173/")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestApiResponse> register(@RequestPart("body") ProductRegisterRequest product,
                                                    @RequestPart("images")List<MultipartFile> images,
                                                    HttpServletRequest request){
        System.out.println(images);
        // 요청 헤더 출력
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                System.out.println(headerName + ": " + request.getHeader(headerName));
            }
        }
        // 요청 바디 출력 (JSON 문자열)
        System.out.println("Request Body:");
        System.out.println(product);
        // user는 임의로 하나 만들어서 넣고 사용하겠음.
        // jwt를 통해 인증을 받아야 하는 로직 확인 후 수정 예정임

          productService.registerProduct(product, images);
        RestApiResponse response = new RestApiResponse(ProductSuccessCode.PRODUCT_REGISTER_SUCCESS);

        return ResponseEntity.ok(response);
    }
//    @GetMapping("/{productId}")
//    public ResponseEntity<RestApiResponse> productDetail(@PathVariable("productId") Long productId){
//
//
//    }
}

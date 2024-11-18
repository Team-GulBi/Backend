package com.gulbi.Backend.domain.rental.product.controller;

import com.gulbi.Backend.domain.rental.product.dto.TestDto;
import com.gulbi.Backend.domain.rental.product.response.ErrorCode;
import com.gulbi.Backend.domain.rental.product.response.SuccessCode;
import com.gulbi.Backend.domain.rental.product.dto.ProductRegistrationDto;
import com.gulbi.Backend.domain.rental.product.service.ProductService;
import com.gulbi.Backend.global.response.RestApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestApiResponse> registerProduct(
            @RequestPart("body") TestDto product,
            // @RequestPart("images") List<MultipartFile> images,
            HttpServletRequest request) {

        // 디버깅: 요청 바디 출력
        System.out.println("Request Body:");
        System.out.println(product);

        // 디버깅: 이미지 목록 출력
        System.out.println("Images:");
        // System.out.println(images);

        // 요청 헤더 출력
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                System.out.println(headerName + ": " + request.getHeader(headerName));
            }
        }
        // ProductService에 ProductRegistrationDto와 List<String> image 전달
        //productService.registerProduct(product,images);

        // 성공 응답 반환
        return ResponseEntity.ok(new RestApiResponse(SuccessCode.PRODUCT_REGISTER_SUCCESS));
    }

    @DeleteMapping
    public ResponseEntity<RestApiResponse> deleteProduct(@RequestBody Map<String, Long> body) {
        Long boardId = body.get("boardId");
        boolean isDeleted = productService.deleteProductById(boardId);
        if (isDeleted) {
            return ResponseEntity.ok(new RestApiResponse(SuccessCode.PRODUCT_DELETE_SUCCESS));
        } else {
            return ResponseEntity.ok(new RestApiResponse(ErrorCode.PRODUCT_DELETE_FAIL));
        }
    }

}



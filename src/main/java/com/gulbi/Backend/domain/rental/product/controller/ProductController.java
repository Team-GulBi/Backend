package com.gulbi.Backend.domain.rental.product.controller;

import com.gulbi.Backend.domain.rental.product.code.ProductSuccessCode;
import com.gulbi.Backend.domain.rental.product.dto.response.ProductDetailResponseDto;
import com.gulbi.Backend.domain.rental.product.dto.request.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.service.image.ImageService;
import com.gulbi.Backend.domain.rental.product.service.ProductService;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;
import com.gulbi.Backend.domain.user.response.SuccessCode;
import com.gulbi.Backend.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ImageService imageService;


    @CrossOrigin("http://localhost:5173/")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestApiResponse> register(@RequestPart("body") ProductRegisterRequestDto product,
                                                    @RequestParam("images") List<MultipartFile> images) throws IOException {
            ProductImageCollection productImageCollection = ProductImageCollection.of(images);
            product.setProductImageCollection(productImageCollection);
            productService.registerProduct(product);
            RestApiResponse response = new RestApiResponse(ProductSuccessCode.PRODUCT_REGISTER_SUCCESS);


        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<RestApiResponse> productDetail(@PathVariable("productId") Long productId) {
        ProductDetailResponseDto data = productService.getProductDetail(productId);
        RestApiResponse response = new RestApiResponse(SuccessCode.REGISTER_SUCCESS,data);
        return ResponseEntity.ok(response);
    }

//    @GetMapping("/{query}/{filtering}")
//    public ResponseEntity<RestApiResponse> searchProduct(@PathVariable("query")String query, @PathVariable("filtering")String filtering){
//        long startTime = System.currentTimeMillis();
//        System.out.println(query);
////            List<ProductResponseProjection> data =productService.searchProductWithTitle(query);
//            List<ProductResponseDto> data =productService.searchProductWithTitle(query);
//            RestApiResponse response = new RestApiResponse(SuccessCode.REGISTER_SUCCESS,data);
//        long endTime = System.currentTimeMillis(); // 끝나는 시간 기록
//        long executionTime = endTime - startTime; // 실행 시간 계산
//        System.out.println("Execution time: " + executionTime + " ms");
//        return ResponseEntity.ok(response);
//    }
}

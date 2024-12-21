package com.gulbi.Backend.domain.rental.product.controller;

import com.gulbi.Backend.domain.rental.product.code.ProductSuccessCode;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.*;
import com.gulbi.Backend.domain.rental.product.dto.product.response.ProductDetailResponseDto;
import com.gulbi.Backend.domain.rental.product.service.product.ProductService;
import com.gulbi.Backend.global.response.RestApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @RequestBody(content = @Content(
            encoding = @Encoding(name = "request", contentType = MediaType.APPLICATION_JSON_VALUE)))
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "상품등록",
            description = "상품정보, 상품이미지를 이용하여 상품을 등록 합니다."
    )
    public ResponseEntity<RestApiResponse> register(
            @Parameter(description = "상품정보", required = true, content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE)) @RequestPart("body") ProductRegisterRequestDto productInfo
            ,
            @Parameter(description = "상품 이미지 파일", required = true)
            @RequestPart("images") List<MultipartFile> productImages)
    {
            productService.registrationProduct(productInfo,ProductImageCreateRequestDto.of(productImages));
            RestApiResponse response = new RestApiResponse(ProductSuccessCode.PRODUCT_REGISTER_SUCCESS);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}")
    @Operation(
            summary = "상품의 상세정보 조회",
            description = "상품의 PK값으로 상품의 상세정보 조회"
    )
    public ResponseEntity<RestApiResponse> productDetail(@PathVariable("productId") Long productId) {
        ProductDetailResponseDto data = productService.getProductDetail(productId);
        RestApiResponse response = new RestApiResponse(ProductSuccessCode.PRODUCT_FOUND_SUCCESS,data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/{query}/{detail}")
    @Operation(
            summary = "조건에 맞는 상품 검색",
            description = "query는 검색어, detail은 필터(태그별,제목별,위치별 등등)"
    )
    public ResponseEntity<RestApiResponse> searchProduct(@Parameter(description = "검색어",required = true)@PathVariable("query")String query,
                                                         @Parameter(description = "필터",required = true)@PathVariable("detail")String detail){
        ProductSearchRequestDto productSearchRequestDto = ProductSearchRequestDto.of(detail, query);
        List<ProductOverViewResponse> data = productService.searchProductOverview(productSearchRequestDto);
        RestApiResponse response = new RestApiResponse(ProductSuccessCode.PRODUCT_FOUND_SUCCESS,data);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/views/{productId}")
    public ResponseEntity<RestApiResponse> updateProductViews(@PathVariable("productId") Long productId){
        productService.updateProductViews(productId);
        RestApiResponse response = new RestApiResponse(ProductSuccessCode.PRODUCT_VIEWS_UPDATED_SUCCESS);
        return ResponseEntity.ok(response);

    }

    @PatchMapping()
    public ResponseEntity<RestApiResponse> updateProduct(@RequestPart(value = "productInfo")ProductUpdateRequestDto productUpdateRequestDto,
                                                         @ModelAttribute("images") ProductImageCreateRequestDto productImageCreateRequestDto,
                                                         @RequestPart(value = "category", required = false) ProductCategoryUpdateRequestDto productCategoryUpdateRequestDto,
                                                         @RequestPart(value = "imagesDelete", required = false)ProductImageDeleteRequestDto productImageDeleteRequestDto)
    {
        productService.updateProduct(productUpdateRequestDto, productCategoryUpdateRequestDto, productImageDeleteRequestDto,productImageCreateRequestDto);
        RestApiResponse response = new RestApiResponse(ProductSuccessCode.PRODUCT_INFO_UPDATED_SUCCESS);
        return ResponseEntity.ok(response);
    }
}

package com.gulbi.Backend.domain.rental.product.controller;

import com.gulbi.Backend.domain.rental.product.code.ProductSuccessCode;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.*;
import com.gulbi.Backend.domain.rental.product.dto.product.response.ProductDetailResponseDto;
import com.gulbi.Backend.domain.rental.product.service.product.ProductCrudService;
import com.gulbi.Backend.domain.rental.product.service.product.ProductService;
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
    private final ProductCrudService productCrudService;


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RestApiResponse> register(@RequestPart("body") ProductRegisterRequestDto productInfo,
                                                    @ModelAttribute("images") ProductImageCreateRequestDto images) throws IOException {
            productService.registrationProduct(productInfo,images);
            RestApiResponse response = new RestApiResponse(ProductSuccessCode.PRODUCT_REGISTER_SUCCESS);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<RestApiResponse> productDetail(@PathVariable("productId") Long productId) {
        ProductDetailResponseDto data = productService.getProductDetail(productId);
        RestApiResponse response = new RestApiResponse(SuccessCode.REGISTER_SUCCESS,data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/{query}/{detail}")
    public ResponseEntity<RestApiResponse> searchProduct(@PathVariable("query")String query, @PathVariable("detail")String detail){
        ProductSearchRequestDto productSearchRequestDto = ProductSearchRequestDto.of(detail, query);
        List<ProductOverViewResponse> data = productService.searchProductOverview(productSearchRequestDto);
        RestApiResponse response = new RestApiResponse(SuccessCode.REGISTER_SUCCESS,data);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/views/{productId}")
    public ResponseEntity<RestApiResponse> updateProductViews(@PathVariable("productId") Long productId){
        productService.updateProductViews(productId);
        RestApiResponse response = new RestApiResponse(SuccessCode.REGISTER_SUCCESS);
        return ResponseEntity.ok(response);

    }

    @PatchMapping()
    public ResponseEntity<RestApiResponse> updateProduct(@RequestPart(value = "productInfo")ProductUpdateRequestDto productUpdateRequestDto,
                                                         @ModelAttribute("images") ProductImageCreateRequestDto productImageCreateRequestDto,
                                                         @RequestPart(value = "category", required = false) ProductCategoryUpdateRequestDto productCategoryUpdateRequestDto,
                                                         @RequestPart(value = "imagesDelete", required = false)ProductImageDeleteRequestDto productImageDeleteRequestDto)
    {
        System.out.println(productImageCreateRequestDto.getProductImageCollection());
        productService.updateProduct(productUpdateRequestDto, productCategoryUpdateRequestDto, productImageDeleteRequestDto,productImageCreateRequestDto);
        RestApiResponse response = new RestApiResponse(SuccessCode.REGISTER_SUCCESS);
        return ResponseEntity.ok(response);
    }
}

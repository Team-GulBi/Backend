package com.gulbi.Backend.domain.rental.product.response;

import com.gulbi.Backend.global.response.ResponseApiCode;
import org.springframework.http.HttpStatus;

public enum SuccessCode implements ResponseApiCode {
    // 상품 등록 성공
    PRODUCT_REGISTER_SUCCESS(HttpStatus.CREATED,"M001","상품등록완료"),
    // 상품 수정 성공
    PRODUCT_UPDATE_SUCCESS(HttpStatus.OK, "P002", "상품수정완료"),
    // 상품 삭제 성공
    PRODUCT_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "P003", "상품삭제완료"),
    //  상품 조회 성공
    PRODUCT_RETRIEVE_SUCCESS(HttpStatus.OK, "P004", "상품검색완료"),
    // 상품 상세 조회 성공
    PRODUCT_DETAILS_RETRIEVE_SUCCESS(HttpStatus.OK, "P005", "상품검색완료");



    private HttpStatus status;
    private String code;
    private String message;


    SuccessCode(HttpStatus status, String code, String message) {
        this.status=status;
        this.code=code;
        this.message=message;
    }

    @Override
    public HttpStatus getStatus() {
        return this.status;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}

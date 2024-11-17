package com.gulbi.Backend.domain.rental.product.response;

import com.gulbi.Backend.global.response.ResponseApiCode;
import org.springframework.http.HttpStatus;

public enum SuccessCode implements ResponseApiCode {
    // 카테고리 관련 성공 코드
    CATEGORY_FETCH_SUCCESS(HttpStatus.OK, "C001", "카테고리 조회에 성공했습니다."),
    // 상품 등록 성공
    PRODUCT_REGISTER_SUCCESS(HttpStatus.CREATED,"M001","상품이 성공적으로 등록 되었습니다."),
    // 상품 수정 성공
    PRODUCT_UPDATE_SUCCESS(HttpStatus.OK, "P003", "상품 정보가 성공적으로 수정되었습니다."),
    // 상품 삭제 성공
    PRODUCT_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "P004", "상품이 성공적으로 삭제되었습니다."),
    //  상품 조회 성공
    PRODUCT_RETRIEVE_SUCCESS(HttpStatus.OK, "P005", "상품 정보가 성공적으로 조회되었습니다.");


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

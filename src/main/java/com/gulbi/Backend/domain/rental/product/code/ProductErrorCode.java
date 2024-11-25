package com.gulbi.Backend.domain.rental.product.code;

import com.gulbi.Backend.global.response.ResponseApiCode;
import org.springframework.http.HttpStatus;

public enum ProductErrorCode implements ResponseApiCode {
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND,"E001","해당 상품 ID와 일치하는 상품을 찾을 수 없습니다."),
    PRODUCT_NOT_FOUND_BY_TITLE(HttpStatus.NOT_FOUND,"E002", "해당 제목을 가진 상품이 존재하지 않습니다."),
    PRODUCT_NOT_FOUND_BY_TAGS(HttpStatus.NOT_FOUND,"E003","해당 태그를 가진 상품이 존재하지 않습니다.");
    private HttpStatus status;
    private String code;
    private String message;

    ProductErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code=code;
        this.message=message;
    }

    @Override
    public HttpStatus getStatus() {
        return null;
    }

    @Override
    public String getCode() {
        return "";
    }

    @Override
    public String getMessage() {
        return "";
    }
}

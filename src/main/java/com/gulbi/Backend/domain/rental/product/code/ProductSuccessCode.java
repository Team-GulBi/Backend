package com.gulbi.Backend.domain.rental.product.code;
import com.gulbi.Backend.global.response.ResponseApiCode;
import org.springframework.http.HttpStatus;

public enum ProductSuccessCode implements ResponseApiCode {

    PRODUCT_REGISTER_SUCCESS(HttpStatus.OK,"P001","상품이 성공적으로 등록 되었습니다.");

    private HttpStatus status;
    private String code;
    private String message;

    ProductSuccessCode(HttpStatus status, String code, String message) {
        this.status = status;
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

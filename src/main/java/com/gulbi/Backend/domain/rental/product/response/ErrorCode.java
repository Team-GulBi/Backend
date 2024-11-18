package com.gulbi.Backend.domain.rental.product.response;

import com.gulbi.Backend.global.response.ResponseApiCode;
import org.springframework.http.HttpStatus;

public enum ErrorCode implements ResponseApiCode {

    // 상품 등록 실패
    PRODUCT_REGISTER_FAIL(HttpStatus.BAD_REQUEST, "M001", "상품등록실패"),
    // 상품 수정 실패
    PRODUCT_UPDATE_FAIL(HttpStatus.NOT_FOUND, "P002", "상품수정실패"),
    // 상품 삭제 실패
    PRODUCT_DELETE_FAIL(HttpStatus.NOT_FOUND, "P003", "상품삭제실패"),
    // 상품 조회 실패
    PRODUCT_RETRIEVE_FAIL(HttpStatus.NOT_FOUND, "P004", "상품검색실패"),
    // 상품 상세 조회 실패
    PRODUCT_DETAILS_RETRIEVE_FAIL(HttpStatus.NOT_FOUND, "P005", "상품상세검색실패"),
    // 카테고리 조회 실패
    CATEGORY_FETCH_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "C001", "카테고리 조회에 실패했습니다."),
    // 데이터베이스 처리 오류
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DB001", "데이터베이스 처리 중 오류가 발생했습니다."),
    // 서버 오류
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S500", "서버에서 문제가 발생했습니다. 나중에 다시 시도해주세요.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
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

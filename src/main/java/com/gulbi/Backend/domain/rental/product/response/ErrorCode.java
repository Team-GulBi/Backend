package com.gulbi.Backend.domain.rental.product.response;
import com.gulbi.Backend.global.response.ResponseApiCode;
import org.springframework.http.HttpStatus;

public enum ErrorCode implements ResponseApiCode {

    // 카테고리 관련 오류
    CATEGORY_FETCH_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, "C001", "카테고리 조회에 실패했습니다."),
    // 상품 등록 실패 (예: 필수 값이 누락되었을 때)
    PRODUCT_REGISTER_FAIL(HttpStatus.BAD_REQUEST, "P101", "상품 등록에 실패했습니다."),
    // 상품 수정 실패 (예: 존재하지 않는 상품 수정 시도)
    PRODUCT_UPDATE_FAIL(HttpStatus.NOT_FOUND, "P102", "수정하려는 상품을 찾을 수 없습니다."),
    // 상품 삭제 실패 (예: 삭제할 상품이 존재하지 않을 때)
    PRODUCT_DELETE_FAIL(HttpStatus.NOT_FOUND, "P103", "삭제하려는 상품을 찾을 수 없습니다."),
    // 상품 조회 실패 (예: 조회할 상품이 존재하지 않을 때)
    PRODUCT_RETRIEVE_FAIL(HttpStatus.NOT_FOUND, "P104", "조회하려는 상품을 찾을 수 없습니다."),
    // 데이터베이스 오류 (예: DB 저장 실패 시)
    DATABASE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "P105", "데이터베이스 처리 중 오류가 발생했습니다."),
    // 서버 에러 (예: 상품 등록 중 서버 문제 발생)
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "P500", "서버에서 문제가 발생했습니다. 나중에 다시 시도하세요.");

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
        return status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

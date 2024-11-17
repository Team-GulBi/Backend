package com.gulbi.Backend.domain.rental.product.exception;

import com.gulbi.Backend.global.error.BusinessException;
import com.gulbi.Backend.global.response.ResponseApiCode;
import com.gulbi.Backend.domain.rental.product.response.ErrorCode;

public abstract class ProductException extends BusinessException {

    public ProductException(ResponseApiCode errorCode) {
        super(errorCode);
    }

    // 카테고리 조회 실패 예외
    public static class CategoryFetchFailedException extends ProductException {
        public CategoryFetchFailedException() {super(ErrorCode.CATEGORY_FETCH_FAIL);}
    }

    // 상품 등록 실패 예외
    public static class ProductRegistrationFailedException extends ProductException {
        public ProductRegistrationFailedException() {
            super(ErrorCode.PRODUCT_REGISTER_FAIL);
        }
    }

    // 상품 수정 실패 예외
    public static class ProductUpdateFailedException extends ProductException {
        public ProductUpdateFailedException() {
            super(ErrorCode.PRODUCT_UPDATE_FAIL);
        }
    }

    // 상품 삭제 실패 예외
    public static class ProductDeleteFailedException extends ProductException {
        public ProductDeleteFailedException() {
            super(ErrorCode.PRODUCT_DELETE_FAIL);
        }
    }

    // 상품 조회 실패 예외
    public static class ProductRetrieveFailedException extends ProductException {
        public ProductRetrieveFailedException() {
            super(ErrorCode.PRODUCT_RETRIEVE_FAIL);
        }
    }

    // 데이터베이스 오류 예외
    public static class DatabaseException extends ProductException {
        public DatabaseException() {
            super(ErrorCode.DATABASE_ERROR);
        }
    }

    // 서버 오류 예외
    public static class InternalServerErrorException extends ProductException {
        public InternalServerErrorException() {
            super(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}

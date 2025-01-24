package com.gulbi.Backend.domain.rental.product.exception;

import com.gulbi.Backend.global.error.BusinessException;
import com.gulbi.Backend.global.error.ExceptionMetaData;
import com.gulbi.Backend.global.response.ResponseApiCode;

public abstract class CategoryException extends BusinessException {
    // 생성자에서 ExceptionMetaDataDto를 받도록 수정
    public CategoryException(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
        super(errorCode, exceptionMetaData);
    }

    public static class NotInitializedCategoryException extends CategoryException {
        public NotInitializedCategoryException(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
            super(errorCode, exceptionMetaData);
        }
    }

    public static class CategoryNotFoundException extends CategoryException {
        public CategoryNotFoundException(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
            super(errorCode, exceptionMetaData);
        }
    }
}

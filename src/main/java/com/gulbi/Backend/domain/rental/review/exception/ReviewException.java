package com.gulbi.Backend.domain.rental.review.exception;

import com.gulbi.Backend.global.error.BusinessException;
import com.gulbi.Backend.global.error.ExceptionMetaData;
import com.gulbi.Backend.global.response.ResponseApiCode;

public class ReviewException extends BusinessException {
    // 생성자에서 ResponseApiCode와 ExceptionMetaDataDto를 받도록 수정
    public ReviewException(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
        super(errorCode, exceptionMetaData);
    }

    public static class ReviewNotFoundException extends ReviewException {
        public ReviewNotFoundException(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
            super(errorCode, exceptionMetaData);
        }
    }

    public static class ReviewValidationException extends ReviewException {
        public ReviewValidationException(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
            super(errorCode, exceptionMetaData);
        }
    }

    public static class DatabaseErrorException extends ReviewException {
        public DatabaseErrorException(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
            super(errorCode, exceptionMetaData);
        }
    }

    public static class MissingReviewFiledException extends ReviewException {
        public MissingReviewFiledException(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
            super(errorCode, exceptionMetaData);
        }
    }
}

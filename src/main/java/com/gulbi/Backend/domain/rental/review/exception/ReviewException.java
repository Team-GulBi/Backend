package com.gulbi.Backend.domain.rental.review.exception;

import com.gulbi.Backend.global.error.BusinessException;
import com.gulbi.Backend.global.response.ResponseApiCode;

public class ReviewException extends BusinessException {
    public ReviewException(ResponseApiCode errorCode) {
        super(errorCode);
    }

    public static class ReviewNotFoundException extends ReviewException {
        public ReviewNotFoundException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }

    public static class ReviewValidationException extends ReviewException {
        public ReviewValidationException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }

    public static class DatabaseErrorException extends ReviewException {
        public DatabaseErrorException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }

    public static class MissingReviewFiledException extends ReviewException {
        public MissingReviewFiledException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }
}

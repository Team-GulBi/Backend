package com.gulbi.Backend.domain.rental.product.exception;

import com.gulbi.Backend.global.error.BusinessException;
import com.gulbi.Backend.global.response.ResponseApiCode;

public abstract class CategoryException extends BusinessException {
    public CategoryException(ResponseApiCode errorCode) {
        super(errorCode);
    }

    public static class NotInitializedCategoryException extends CategoryException {
        public NotInitializedCategoryException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }

    public static class CategoryNotFoundException extends CategoryException {
        public CategoryNotFoundException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }
}

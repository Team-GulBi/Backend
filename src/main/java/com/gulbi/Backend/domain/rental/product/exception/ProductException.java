package com.gulbi.Backend.domain.rental.product.exception;
import com.gulbi.Backend.global.error.BusinessException;
import com.gulbi.Backend.global.response.ResponseApiCode;

public abstract class ProductException extends BusinessException {
    public ProductException(ResponseApiCode errorCode) {
        super(errorCode);
    }

    public static class ProductNotFoundException extends ProductException {
        public ProductNotFoundException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }

    public static class NoProductFoundForTitleException extends ProductException {
        public NoProductFoundForTitleException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }

    public static class NoProductFoundForTagsException extends ProductException {
        public NoProductFoundForTagsException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }

    public static class NoUpdateProductException extends ProductException{
        public NoUpdateProductException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }
}

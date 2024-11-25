package com.gulbi.Backend.domain.rental.product.exception;

import com.gulbi.Backend.domain.rental.product.code.ProductErrorCode;
import com.gulbi.Backend.domain.rental.product.code.ProductSuccessCode;
import com.gulbi.Backend.global.error.BusinessException;
import com.gulbi.Backend.global.response.ResponseApiCode;

public abstract class ProductException extends BusinessException {
    public ProductException(ResponseApiCode errorCode) {
        super(errorCode);
    }

    public static class ProductNotFoundException extends ProductException {
        public ProductNotFoundException(ResponseApiCode errorCode) {
            super(ProductErrorCode.PRODUCT_NOT_FOUND);
        }
    }

    public static class NoProductFoundForTitleException extends ProductException {
        public NoProductFoundForTitleException(ResponseApiCode errorCode) {
            super(ProductErrorCode.PRODUCT_NOT_FOUND_BY_TITLE);
        }
    }

    public static class NoProductFoundForTagsException extends ProductException {
        public NoProductFoundForTagsException(ResponseApiCode errorCode) {
            super(ProductErrorCode.PRODUCT_NOT_FOUND_BY_TAGS);
        }
    }

    public static class NoUpdateProductException extends ProductException{
        public NoUpdateProductException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }
}

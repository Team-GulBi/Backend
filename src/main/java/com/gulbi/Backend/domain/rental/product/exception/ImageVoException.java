package com.gulbi.Backend.domain.rental.product.exception;

import com.gulbi.Backend.global.error.BusinessException;
import com.gulbi.Backend.global.error.ExceptionMetaData;
import com.gulbi.Backend.global.response.ResponseApiCode;

public abstract class ImageVoException extends BusinessException {
    public ImageVoException(ResponseApiCode code, ExceptionMetaData exceptionMetaData) {
        super(code, exceptionMetaData);
    }

    public static class ImageUrlNotFoundException extends ImageVoException {
        public ImageUrlNotFoundException(ResponseApiCode code, ExceptionMetaData exceptionMetaData) {
            super(code, exceptionMetaData);
        }
    }

    public static class NotValidatedImageUrlException extends ImageVoException {
        public NotValidatedImageUrlException(ResponseApiCode code, ExceptionMetaData exceptionMetaData) {
            super(code, exceptionMetaData);
        }
    }

    public static class ImageCollectionIsEmptyException extends ImageVoException {
        public ImageCollectionIsEmptyException(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
            super(errorCode, exceptionMetaData);
        }
    }
}

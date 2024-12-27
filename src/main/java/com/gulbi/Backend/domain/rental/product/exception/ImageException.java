package com.gulbi.Backend.domain.rental.product.exception;

import com.gulbi.Backend.global.error.BusinessException;
import com.gulbi.Backend.global.response.ResponseApiCode;

public abstract class ImageException extends BusinessException {
    public ImageException(ResponseApiCode errorCode) {
        super(errorCode);
    }

    public static class NotUploadImageToS3Exception extends ImageException {
        public NotUploadImageToS3Exception(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }

    public static class NotContainedImageIdException extends ImageException{
        public NotContainedImageIdException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }
    public static class ImageDeleteValidationException extends ImageException {
        public ImageDeleteValidationException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }

    public static class ImageDeleteFailedException extends ImageException {
        public ImageDeleteFailedException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }

    public static class InvalidProductImageIdException extends ImageException {
        public InvalidProductImageIdException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }

    public static class DatabaseErrorException extends ImageException {
        public DatabaseErrorException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }
}

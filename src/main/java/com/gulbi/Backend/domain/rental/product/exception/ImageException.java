package com.gulbi.Backend.domain.rental.product.exception;

import com.gulbi.Backend.global.error.BusinessException;
import com.gulbi.Backend.global.error.ExceptionMetaData;
import com.gulbi.Backend.global.response.ResponseApiCode;

public abstract class ImageException extends BusinessException {
    public ImageException(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
        super(errorCode, exceptionMetaData);
    }

    public static class NotUploadImageToS3Exception extends ImageException {
        public NotUploadImageToS3Exception(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
            super(errorCode, exceptionMetaData);
        }
    }

    public static class NotContainedImageIdException extends ImageException {
        public NotContainedImageIdException(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
            super(errorCode, exceptionMetaData);
        }
    }

    public static class ImageDeleteValidationException extends ImageException {
        public ImageDeleteValidationException(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
            super(errorCode, exceptionMetaData);
        }
    }

    public static class ImageDeleteFailedException extends ImageException {
        public ImageDeleteFailedException(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
            super(errorCode, exceptionMetaData);
        }
    }

    public static class InvalidProductImageIdException extends ImageException {
        public InvalidProductImageIdException(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
            super(errorCode, exceptionMetaData);
        }
    }

    public static class DatabaseErrorException extends ImageException {
        public DatabaseErrorException(ResponseApiCode errorCode, ExceptionMetaData exceptionMetaData) {
            super(errorCode, exceptionMetaData);
        }
    }
}

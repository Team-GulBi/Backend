package com.gulbi.Backend.domain.rental.product.exception;

import com.gulbi.Backend.global.error.BusinessException;
import com.gulbi.Backend.global.response.ResponseApiCode;

public abstract class ImageVoException extends BusinessException {
    public ImageVoException(ResponseApiCode code) {
        super(code);
    }

    public static class ImageUrlNotFoundException extends ImageVoException {
      public ImageUrlNotFoundException(ResponseApiCode code) {
        super(code);
      }
    }

    public static class NotValidatedImageUrlException extends ImageVoException{

        public NotValidatedImageUrlException(ResponseApiCode code) {
            super(code);
        }
    }

    public static class ImageCollectionIsEmptyException extends ImageException{

        public ImageCollectionIsEmptyException(ResponseApiCode errorCode) {
            super(errorCode);
        }
    }
}

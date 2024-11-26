package com.gulbi.Backend.domain.rental.product.exception;

import com.gulbi.Backend.global.error.BusinessException;
import com.gulbi.Backend.global.response.ResponseApiCode;

public abstract class ImageUrlCollectionException extends BusinessException {
    public ImageUrlCollectionException(ResponseApiCode code) {
        super(code);
    }

    public static class ImageUrlNotFoundException extends ImageUrlCollectionException {
      public ImageUrlNotFoundException(ResponseApiCode code) {
        super(code);
      }
    }
}

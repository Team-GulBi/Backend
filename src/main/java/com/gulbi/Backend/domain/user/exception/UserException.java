package com.gulbi.Backend.domain.user.exception;

import com.gulbi.Backend.domain.user.response.ErrorCode;
import com.gulbi.Backend.global.error.BusinessException;
import com.gulbi.Backend.global.response.ResponseApiCode;
import com.gulbi.Backend.global.response.RestApiResponse;

public abstract class UserException extends BusinessException {
    public UserException(ResponseApiCode errorCode){
        super(errorCode);
    }
    public static class UserNotFoundException extends UserException{
        public UserNotFoundException(){
            super(ErrorCode.USER_NOT_FOUND);
        }

    };
}

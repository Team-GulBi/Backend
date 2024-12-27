package com.gulbi.Backend.global.error;

import com.gulbi.Backend.global.response.ResponseApiCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class BusinessException extends RuntimeException{
    private HttpStatus status;
    private String code;
    private ResponseApiCode responseApiCode;

    public BusinessException(ResponseApiCode errorCode){
        super(errorCode.getMessage());
        this.responseApiCode = errorCode;
        this.status=errorCode.getStatus();
        this.code=errorCode.getCode();
    }

}



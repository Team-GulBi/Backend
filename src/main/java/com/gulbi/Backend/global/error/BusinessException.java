package com.gulbi.Backend.global.error;

import com.gulbi.Backend.global.response.ResponseApiCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class BusinessException extends RuntimeException{
    private HttpStatus status;
    private String code;
    private ResponseApiCode responseApiCode;
    private ExceptionMetaData metaDataDto;

    public BusinessException(ResponseApiCode errorCode, ExceptionMetaData metaDataDto){
        super(errorCode.getMessage());
        this.metaDataDto = metaDataDto;
        this.responseApiCode = errorCode;
        this.status=errorCode.getStatus();
        this.code=errorCode.getCode();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public ResponseApiCode getResponseApiCode() {
        return responseApiCode;
    }

    public ExceptionMetaData getMetaDataDto() {
        return metaDataDto;
    }
}



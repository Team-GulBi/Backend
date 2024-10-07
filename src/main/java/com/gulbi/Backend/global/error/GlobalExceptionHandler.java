package com.gulbi.Backend.global.error;

import com.gulbi.Backend.domain.user.response.ErrorCode;
import com.gulbi.Backend.global.response.RestApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<RestApiResponse> handleBusinessException(BusinessException e){
        RestApiResponse response = new RestApiResponse(e.getResponseApiCode());
        return ResponseEntity.status(e.getStatus()).body(response);
    }

}

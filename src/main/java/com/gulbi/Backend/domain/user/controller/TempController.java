package com.gulbi.Backend.domain.user.controller;

import com.gulbi.Backend.domain.user.exception.UserException;
import com.gulbi.Backend.domain.user.response.SuccessCode;
import com.gulbi.Backend.global.response.RestApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/excample")
public class TempController {
    @GetMapping("/sign")
    public ResponseEntity<RestApiResponse> responseentity(){

        RestApiResponse response = new RestApiResponse(SuccessCode.REGISTER_SUCCESS);
        return ResponseEntity.ok(response);

}
}

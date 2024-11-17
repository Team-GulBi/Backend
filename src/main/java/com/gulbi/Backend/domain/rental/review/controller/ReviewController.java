package com.gulbi.Backend.domain.rental.review.controller;

import com.gulbi.Backend.domain.rental.review.dto.ReviewCreateRequestDto;
import com.gulbi.Backend.domain.rental.review.service.ReviewService;
import com.gulbi.Backend.domain.user.response.SuccessCode;
import com.gulbi.Backend.global.response.RestApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ap1/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping
    public ResponseEntity<RestApiResponse> createReview(@RequestBody ReviewCreateRequestDto request){
        reviewService.registerReview(request);
        RestApiResponse response = new RestApiResponse(SuccessCode.REGISTER_SUCCESS);
        return ResponseEntity.ok(response);
    }
}

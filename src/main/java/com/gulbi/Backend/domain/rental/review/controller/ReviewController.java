package com.gulbi.Backend.domain.rental.review.controller;

import com.gulbi.Backend.domain.rental.review.dto.ReviewCreateRequestDto;
import com.gulbi.Backend.domain.rental.review.dto.ReviewUpdateRequestDto;
import com.gulbi.Backend.domain.rental.review.service.ReviewService;
import com.gulbi.Backend.domain.user.response.SuccessCode;
import com.gulbi.Backend.global.response.RestApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ap1/v1/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping
    public ResponseEntity<RestApiResponse> createReview(@RequestBody @Validated ReviewCreateRequestDto request){
        reviewService.registerReview(request);
        RestApiResponse response = new RestApiResponse(SuccessCode.REGISTER_SUCCESS);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<RestApiResponse> deleteReview(@PathVariable("reviewId") Long reviewId){
        reviewService.deleteReview(reviewId);
        RestApiResponse response = new RestApiResponse(SuccessCode.REGISTER_SUCCESS);
        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<RestApiResponse> updateReview(@RequestBody ReviewUpdateRequestDto reviewUpdateRequestDto){
        reviewService.updateReview(reviewUpdateRequestDto);
        RestApiResponse response = new RestApiResponse(SuccessCode.REGISTER_SUCCESS);
        return ResponseEntity.ok(response);
    }
}

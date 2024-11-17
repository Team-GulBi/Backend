package com.gulbi.Backend.domain.rental.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewCreateRequestDto {
    private final Long productId;
    private final Integer rating;
    private final String content;
}

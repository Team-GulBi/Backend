package com.gulbi.Backend.domain.rental.review.dto;

import com.gulbi.Backend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ReviewCreateRequestDto {
    private final Long productId;
    private final Integer rating;
    private final String content;
}

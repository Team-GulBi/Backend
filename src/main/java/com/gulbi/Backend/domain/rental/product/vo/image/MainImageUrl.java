package com.gulbi.Backend.domain.rental.product.vo.image;

import com.gulbi.Backend.domain.rental.product.code.ImageErrorCode;
import com.gulbi.Backend.domain.rental.product.exception.ImageVoException;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public class MainImageUrl {
    private static final String REGEX = "^https://.*";
    private final String mainImageUrl;

    private MainImageUrl(String mainImageUrl) {
        ValidateImageUrl(mainImageUrl);
        this.mainImageUrl = mainImageUrl;
    }
    public static MainImageUrl of(String mainImageUrl){
        return new MainImageUrl(mainImageUrl);
    }

    private void ValidateImageUrl(String imageUrl){
        if (!Pattern.matches(REGEX, imageUrl)){
            throw new ImageVoException.NotValidatedImageUrlException(ImageErrorCode.NOT_VALIDATED_IMAGE_URL);
        }
    }
}

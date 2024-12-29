package com.gulbi.Backend.domain.rental.product.vo.image;

import com.gulbi.Backend.domain.rental.product.code.ImageErrorCode;
import com.gulbi.Backend.domain.rental.product.exception.ImageVoException;
import lombok.Getter;
import org.apache.xmlbeans.impl.regex.Match;

import java.util.regex.Pattern;

public class ImageUrl {
    private static final String REGEX = "^https://.*";
    @Getter
    private final String imageUrl;

    private ImageUrl(String imageUrl) {
        ValidateImageUrl(imageUrl);
        this.imageUrl = imageUrl;
    }

    public static ImageUrl of(String imageUrl){
        return new ImageUrl(imageUrl);
    }

    private void ValidateImageUrl(String imageUrl){
        if (!Pattern.matches(REGEX, imageUrl)){
            throw new ImageVoException.NotValidatedImageUrlException(ImageErrorCode.NOT_VALIDATED_IMAGE_URL);
        }
    }


}

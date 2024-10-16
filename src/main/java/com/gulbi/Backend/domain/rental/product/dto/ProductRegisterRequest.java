package com.gulbi.Backend.domain.rental.product.dto;

import com.gulbi.Backend.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRegisterRequest {
    private String tag;
    private String title;
    private String productName;
    private String price;
    private String sido;
    private String sigungu;
    private String bname;
    private String description;
    private String bcategoryId;
    private String mcategoryId;
    private String scategoryId;
}

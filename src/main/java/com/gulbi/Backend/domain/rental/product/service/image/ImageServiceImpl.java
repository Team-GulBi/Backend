package com.gulbi.Backend.domain.rental.product.service.image;

import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageDeleteRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.register.ProductImageCreateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.update.ProductExistingMainImageUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrlCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//ToDo: imageCrudService에서 삭제 및 업데이트 로직 의존성 주입 후, 유효성 검사 메서드 작성, 퍼사드로 실행 예정
@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{
    private final ImageCrudService imageCrudService;
    @Override
    public void updateProductImages(ProductImageCreateRequestDto toBeAddedImages, ProductImageCreateRequestDto toBeUpdatedMainImage, ProductExistingMainImageUpdateRequestDto toBeUpdateMainImageWithUrl, ProductImageDeleteRequestDto toBeDeletedImages) {

    }
}


//이미지 추가
//        Optional.ofNullable(toBeAddedImages.getProductImageCollection())
//        .ifPresent(dto ->{
//        if(!dto.isEmpty()){
//        toBeAddedImages.setProductId(toBeUpdatedProductInfo.getProductId());
//        imageCrudService.registerImageWithProduct(imageCrudService.uploadImagesToS3(dto), resolveProduct(toBeAddedImages.getProductId()));
//        }
//        });
//        if(toBeUpdatedMainImage !=null) {
//        //메인 이미지 추가
//        Optional.ofNullable(toBeUpdatedMainImage.getProductImageCollection())
//        .ifPresent(dto -> {
//        if (!dto.isEmpty()) {
//        toBeUpdatedMainImage.setProductId(toBeUpdatedProductInfo.getProductId());
//        ImageUrlCollection imageUrlCollection = imageCrudService.uploadImagesToS3(dto);
//                            toBeUpdatedProductInfo.setMainImage(imageUrlCollection.getMainImageUrl().getImageUrl());
//        imageCrudService.registerImageWithProduct(imageUrlCollection, resolveProduct(toBeUpdatedMainImage.getProductId()));
//        }
//        });
//        }else{
//        toBeUpdatedProductInfo.setMainImage(toBeUpdatedMainImageWithUrl.getMainImageUrl());
//        }
//        //이미지 삭제 Todo : 삭제하려는 이미지가 메인 이미지면 X
//        Optional.ofNullable(toBeDeletedImages)
//                        .ifPresent(imageCrudService::deleteImages);
package com.gulbi.Backend.domain.rental.product.service.product.update;

import com.gulbi.Backend.domain.rental.product.dto.product.request.update.ProductCategoryUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.register.ProductImageCreateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageDeleteRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.update.ProductExistingMainImageUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.update.ProductUpdateRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.service.category.CategoryBusinessService;
import com.gulbi.Backend.domain.rental.product.service.image.ImageCrudService;
import com.gulbi.Backend.domain.rental.product.service.product.crud.ProductCrudService;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrlCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
  ToDo : 업데이트 서비스는 동작은 하나, 코드의 결합도가 너무 높아. 리팩터링때 전부 분리할 예정임. 임시임
         UpdateService 숨만쉰채... 발견...
 */

@Service
@RequiredArgsConstructor
public class ProductUpdateServiceImpl implements ProductUpdatingService{
    private final ProductCrudService productCrudService;
    private final CategoryBusinessService categoryBusinessService;
    private final ImageCrudService imageCrudService;
    @Override
    public void updateProductViews(Long productId){
        resolveProduct(productId); // 유효성 검사
        productCrudService.updateProductViews(productId);
    }

    //ToDo: 람다식들 순서 배열 중요함, 추후 분리 할때 해당 메서드를 퍼사드로 하고, 나머지는 분리
    @Override
    public void updateProductInfo(
            ProductUpdateRequestDto toBeUpdatedProductInfo,
            ProductCategoryUpdateRequestDto toBeUpdatedCategories,
            ProductImageCreateRequestDto toBeAddedImages,
            ProductImageCreateRequestDto toBeUpdatedMainImage,
            ProductExistingMainImageUpdateRequestDto toBeUpdatedMainImageWithUrl,
            ProductImageDeleteRequestDto toBeDeletedImages
    ){

        resolveProduct(toBeUpdatedProductInfo.getProductId());
        // 카테고리를 만약 업데이트를 한다면 카테고리 아이디를 기반으로 객체를 만들어서 상품dto에 넣어줘야함.
        Optional.ofNullable(toBeUpdatedCategories)
                .map(categoryBusinessService::resolveCategories)
                .ifPresent(toBeUpdatedProductInfo::setCategoryInProduct);


        //상품 업데이트
        Optional.of(toBeUpdatedProductInfo)
                .ifPresent(productCrudService::updateProductInfo);



    }

    private Product resolveProduct(Long productId){
        return productCrudService.getProductById(productId);
    }

}
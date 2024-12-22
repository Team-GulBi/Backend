package com.gulbi.Backend.domain.rental.product.service.product.register;

import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageCreateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductMainImageCreateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.factory.ProductFactory;
import com.gulbi.Backend.domain.rental.product.service.image.ImageService;
import com.gulbi.Backend.domain.rental.product.service.product.crud.ProductCrudService;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrlCollection;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRegistrationServiceImpl implements ProductRegistrationService{
    private final ImageService imageService;
    private final ProductFactory productFactory;
    private final ProductCrudService productCrudService;

    @Override
    public void registerProduct(ProductRegisterRequestDto productRegisterRequestDto, ProductImageCreateRequestDto productImageCreateRequestDto, ProductMainImageCreateRequestDto productMainImageCreateRequestDto){

        ImageUrl mainImageUrl = uploadImages(productMainImageCreateRequestDto.getProductImageCollection()).getMainImageUrl();
        ImageUrlCollection imageUrlCollection = uploadImages(productImageCreateRequestDto.getProductImageCollection());
        ImageUrlCollection finalImageUrlCollection = imageUrlCollection.append(mainImageUrl);

        productRegisterRequestDto.setMainImage(mainImageUrl);
        Product product = createWithRegisterRequestDto(productRegisterRequestDto);
        saveProduct(product);
        saveImages(finalImageUrlCollection,product);
    }

    private ImageUrlCollection uploadImages(ProductImageCollection productImageCollection){
        return imageService.uploadImagesToS3(productImageCollection);
    }

    private Product createWithRegisterRequestDto(ProductRegisterRequestDto productRegisterRequestDto){
        return productFactory.createWithRegisterRequestDto(productRegisterRequestDto);
    }
    private void saveProduct(Product product){
        productCrudService.saveProduct(product);
    }
    private void saveImages(ImageUrlCollection imageUrlCollection, Product product){
        imageService.registerImageWithProduct(imageUrlCollection,product);
    }
}

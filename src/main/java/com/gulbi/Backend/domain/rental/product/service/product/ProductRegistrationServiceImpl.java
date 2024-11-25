package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageCreateRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.factory.ProductFactory;
import com.gulbi.Backend.domain.rental.product.service.image.ImageService;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrlCollection;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductRegistrationServiceImpl implements ProductRegistrationService{
    private final ImageService imageService;
    private final ProductFactory productFactory;
    private final ProductCrudService productCrudService;

    @Override
    public void registerProduct(ProductRegisterRequestDto productRegisterRequestDto, ProductImageCreateRequestDto productImageCreateRequestDto){
        ImageUrlCollection imageUrlCollection = getImageUrlCollection(productImageCreateRequestDto);
        ImageUrl mainImage = imageUrlCollection.getMainImageUrl();
        productRegisterRequestDto.setMainImage(mainImage);
        Product product = createWithRegisterRequestDto(productRegisterRequestDto);
        saveProduct(product);
        saveImage(imageUrlCollection,product);
    }

    private ImageUrlCollection getImageUrlCollection(ProductImageCreateRequestDto productImageCreateRequestDto){
        return imageService.uploadImagesToS3(productImageCreateRequestDto.getProductImageCollection());
    }

    private Product createWithRegisterRequestDto(ProductRegisterRequestDto productRegisterRequestDto){
        return productFactory.createWithRegisterRequestDto(productRegisterRequestDto);
    }
    private void saveProduct(Product product){
        productCrudService.saveProduct(product);
    }
    private void saveImage(ImageUrlCollection imageUrlCollection, Product product){
        imageService.registerImageWithProduct(imageUrlCollection,product);
    }
}

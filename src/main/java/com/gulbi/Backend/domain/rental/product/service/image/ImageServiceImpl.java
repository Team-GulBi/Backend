package com.gulbi.Backend.domain.rental.product.service.image;

import com.gulbi.Backend.domain.rental.product.code.ImageErrorCode;
import com.gulbi.Backend.domain.rental.product.code.ProductErrorCode;
import com.gulbi.Backend.domain.rental.product.dto.ProductImageDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageDeleteRequestDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.exception.ImageException;
import com.gulbi.Backend.domain.rental.product.exception.ProductException;
import com.gulbi.Backend.domain.rental.product.factory.ImageFactory;
import com.gulbi.Backend.domain.rental.product.repository.ImageRepository;
import com.gulbi.Backend.domain.rental.product.service.product.ProductCrudService;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrlCollection;
import com.gulbi.Backend.domain.rental.product.dto.ProductImageDtoCollection;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageCollection;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;
import com.gulbi.Backend.global.util.FileSender;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ProductCrudService productCrudService;
    private final FileSender fileSender;

    @Override
    public void registerImageWithProduct(ImageUrlCollection imageUrlCollection, Product product) {
        ImageCollection imageCollection = ImageFactory.createImagesFromUrls(imageUrlCollection, product);
        saveImages(imageCollection); //이미지 저장과 메인 이미지를 반환하는 두가지 책임..
    }

    @Override
    public ImageUrlCollection uploadImagesToS3(ProductImageCollection productImageCollection) {
        try{
            List<ImageUrl> imageUrlList = new ArrayList<>();
            for (MultipartFile file : productImageCollection.getProductImages()){
                ImageUrl imageUrl = ImageUrl.of(fileSender.sendFile(file));
                imageUrlList.add(imageUrl);
            }
            return ImageUrlCollection.of(imageUrlList);
        }catch (ImageException e){ // 추후 센더에 예외 생기면 센더에서 예외 호출 예정
            throw new ImageException.NotUploadImageToS3Exception(ImageErrorCode.CANT_UPLOAD_IMAGE_TO_S3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductImageDtoCollection getImageByProductId(Long productId) {
        resolveProduct(productId);
        List<ProductImageDto> images = imageRepository.findByImageWithProduct(productId);
        return ProductImageDtoCollection.of(images);

    }

    @Override
    public void saveImages(ImageCollection imageCollection){
        try {
            imageRepository.saveAll(imageCollection.getImages());
        } catch (DataIntegrityViolationException | JpaSystemException | PersistenceException |
                 IllegalArgumentException e) {
            throw new ImageException.NotUploadImageToS3Exception(ImageErrorCode.CANT_UPLOAD_IMAGE_TO_S3);
        }
    }

    @Override
    public void deleteImages(ProductImageDeleteRequestDto productImageDeleteRequestDto) {
        if(productImageDeleteRequestDto.getImagesId()==null){
            throw new ImageException.NotContainedImageIdException(ImageErrorCode.NOT_CONTAINED_IMAGE_ID);
        }
        try{
            imageRepository.deleteImages(productImageDeleteRequestDto);
        } catch (DataIntegrityViolationException e) {
            throw new ImageException.ImageDeleteValidationException(ImageErrorCode.IMAGE_DELETE_FAILED);
        } catch (JpaSystemException | PersistenceException e) {
            throw new ImageException.DatabaseErrorException(ImageErrorCode.DATABASE_ERROR);
        } catch (IllegalArgumentException e) {
            throw new ImageException.InvalidProductImageIdException(ImageErrorCode.INVALID_IMAGE_ID);
        } catch (Exception e) {
            throw new ImageException.ImageDeleteFailedException(ImageErrorCode.IMAGE_DELETE_FAILED);
        }

    }

    private void resolveProduct(Long productId){
        productCrudService.getProductById(productId);
    }


}

package com.gulbi.Backend.domain.rental.product.service.image;

import com.gulbi.Backend.domain.rental.product.code.ImageErrorCode;
import com.gulbi.Backend.domain.rental.product.dto.ProductImageDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductImageDeleteRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.update.ProductMainImageUpdateDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.exception.ImageException;
import com.gulbi.Backend.domain.rental.product.factory.ImageFactory;
import com.gulbi.Backend.domain.rental.product.repository.ImageRepository;
import com.gulbi.Backend.domain.rental.product.service.product.crud.ProductCrudService;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrlCollection;
import com.gulbi.Backend.domain.rental.product.dto.ProductImageDtoCollection;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageCollection;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;
import com.gulbi.Backend.global.util.FileSender;
import com.gulbi.Backend.global.error.ExceptionMetaData;
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
public class ImageCrudServiceImpl implements ImageCrudService {

    private final ImageRepository imageRepository;
    private final ProductCrudService productCrudService;
    private final FileSender fileSender;

    @Override
    public void registerImagesWithProduct(ImageUrlCollection imageUrlCollection, Product product) {
        ImageCollection imageCollection = ImageFactory.createImages(imageUrlCollection, product);
        saveImages(imageCollection);
    }

    @Override
    public ImageUrlCollection uploadImagesToS3(ProductImageCollection productImageCollection) {
        try {
            List<ImageUrl> imageUrlList = new ArrayList<>();
            for (MultipartFile file : productImageCollection.getProductImages()) {
                ImageUrl imageUrl = ImageUrl.of(fileSender.sendFile(file));
                imageUrlList.add(imageUrl);
            }
            return ImageUrlCollection.of(imageUrlList);
        } catch (ImageException e) {
            throw createImageUploadException(e);
        } catch (IOException e) {
            throw createImageProcessingException(e);
        }
    }

    @Override
    public void updateMainImageFlags(ProductMainImageUpdateDto productMainImageUpdateDto) {
        imageRepository.updateImagesFlagsToTrue(productMainImageUpdateDto.getMainImageUrl().getImageUrl());
    }

    @Override
    public ProductImageDtoCollection getImageByProductId(Long productId) {
        resolveProduct(productId);
        List<ProductImageDto> images = imageRepository.findByImageWithProduct(productId);
        return ProductImageDtoCollection.of(images);
    }

    @Override
    public void saveMainImage(ImageUrl mainImageUrl, Product product) {
        imageRepository.save(ImageFactory.createImageToMain(mainImageUrl, product));
    }

    @Override
    public void clearMainImageFlags(Product product) {
        imageRepository.resetMainImagesByProduct(product);
    }

    @Override
    public void saveImages(ImageCollection imageCollection) {
        try {
            imageRepository.saveAll(imageCollection.getImages());
        } catch (DataIntegrityViolationException | JpaSystemException | PersistenceException | IllegalArgumentException e) {
            throw createImageUploadException(e);
        }
    }

    @Override
    public void deleteImages(ProductImageDeleteRequestDto productImageDeleteRequestDto) {
        if (productImageDeleteRequestDto.getImagesId() == null) {
            throw createImageDeleteValidationException(productImageDeleteRequestDto);
        }

        try {
            imageRepository.deleteImages(productImageDeleteRequestDto);
        } catch (DataIntegrityViolationException e) {
            throw createImageDeleteValidationException(productImageDeleteRequestDto);
        } catch (JpaSystemException | PersistenceException e) {
            throw createImageDatabaseErrorException(e);
        } catch (IllegalArgumentException e) {
            throw createInvalidProductImageIdException(e);
        } catch (Exception e) {
            throw createImageDeleteFailedException(e);
        }
    }

    @Override
    public void removeAllImagesFromProduct(Long productId) {
        imageRepository.deleteAllImagesByProductId(resolveProduct(productId));
    }

    private Product resolveProduct(Long productId) {
        return productCrudService.getProductById(productId);
    }

    private ImageException.NotUploadImageToS3Exception createImageUploadException(Exception e) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(e.getMessage(), this.getClass().getName());
        throw new ImageException.NotUploadImageToS3Exception(ImageErrorCode.CANT_UPLOAD_IMAGE_TO_S3, exceptionMetaData);
    }

    private ImageException.ImageDeleteValidationException createImageDeleteValidationException(ProductImageDeleteRequestDto dto) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(dto, this.getClass().getName());
        throw new ImageException.ImageDeleteValidationException(ImageErrorCode.IMAGE_DELETE_FAILED, exceptionMetaData);
    }

    private ImageException.DatabaseErrorException createImageDatabaseErrorException(Exception e) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(e.getMessage(), this.getClass().getName());
        throw new ImageException.DatabaseErrorException(ImageErrorCode.DATABASE_ERROR, exceptionMetaData);
    }

    private ImageException.InvalidProductImageIdException createInvalidProductImageIdException(Exception e) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(e.getMessage(), this.getClass().getName());
        throw new ImageException.InvalidProductImageIdException(ImageErrorCode.INVALID_IMAGE_ID, exceptionMetaData);
    }

    private ImageException.ImageDeleteFailedException createImageDeleteFailedException(Exception e) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(e.getMessage(), this.getClass().getName());
        throw new ImageException.ImageDeleteFailedException(ImageErrorCode.IMAGE_DELETE_FAILED, exceptionMetaData);
    }

    private RuntimeException createImageProcessingException(IOException e) {
        ExceptionMetaData exceptionMetaData = new ExceptionMetaData(e.getMessage(), this.getClass().getName());
        throw new RuntimeException("Error during file processing");
    }
}

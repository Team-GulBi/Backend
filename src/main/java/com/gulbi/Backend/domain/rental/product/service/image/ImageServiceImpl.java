package com.gulbi.Backend.domain.rental.product.service.image;

import com.gulbi.Backend.domain.rental.product.dto.ProductImageDto;
import com.gulbi.Backend.domain.rental.product.entity.Image;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.factory.ImageFactory;
import com.gulbi.Backend.domain.rental.product.repository.ImageRepository;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrls;
import com.gulbi.Backend.domain.rental.product.dto.ProductImageDtos;
import com.gulbi.Backend.domain.rental.product.vo.image.Images;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImages;
import com.gulbi.Backend.global.util.FileSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final FileSender fileSender;

    @Override
    public void registerImageWithProduct(ProductImages productImages, Product product) {
        ImageUrls imageUrls = uploadImagesToS3(productImages);
        Images images = ImageFactory.createImagesFromUrls(imageUrls, product);
        saveImages(images);
    }

    @Override
    public ImageUrls uploadImagesToS3(ProductImages productImages) {
        try{
            List<ImageUrl> imageUrlList = new ArrayList<>();
            for (MultipartFile file : productImages.getProductImages()){
                ImageUrl imageUrl = ImageUrl.of(fileSender.sendFile(file));
                imageUrlList.add(imageUrl);
            }
            return ImageUrls.of(imageUrlList);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public ProductImageDtos getImageByProductId(Long productId) {
        List<ProductImageDto> images = imageRepository.findByImageWithProduct(productId);
        return ProductImageDtos.of(images);

    }

    @Override
    public void saveImages(Images images){
        imageRepository.saveAll(images.getImages());
    }


}

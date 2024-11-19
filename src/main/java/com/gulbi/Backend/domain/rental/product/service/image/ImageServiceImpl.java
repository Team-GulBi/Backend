package com.gulbi.Backend.domain.rental.product.service.image;

import com.gulbi.Backend.domain.rental.product.dto.ProductImageDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.factory.ImageFactory;
import com.gulbi.Backend.domain.rental.product.repository.ImageRepository;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrlCollection;
import com.gulbi.Backend.domain.rental.product.dto.ProductImageDtos;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageCollection;
import com.gulbi.Backend.domain.rental.product.vo.image.ProductImageCollection;
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
    public void registerImageWithProduct(ProductImageCollection productImageCollection, Product product) {
        ImageUrlCollection imageUrlCollection = uploadImagesToS3(productImageCollection);
        ImageCollection imageCollection = ImageFactory.createImagesFromUrls(imageUrlCollection, product);
        saveImages(imageCollection);
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
    public void saveImages(ImageCollection imageCollection){
        imageRepository.saveAll(imageCollection.getImages());
    }


}

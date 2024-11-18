package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductRegisterDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.request.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.response.ProductDetailResponseDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.factory.ProductFactory;
import com.gulbi.Backend.domain.rental.product.service.category.CategoryBusinessService;
import com.gulbi.Backend.domain.rental.product.service.image.ImageService;
import com.gulbi.Backend.domain.rental.product.vo.ProductImageDtoList;
import com.gulbi.Backend.domain.rental.review.dto.ReviewWithAvgProjection;
import com.gulbi.Backend.domain.rental.review.service.ReviewService;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.repository.UserRepository;
import com.gulbi.Backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductCrudService productCrudService;
    private final ImageService imageService;
    private final ReviewService reviewService;
    private final ProductFactory productFactory;

    // Public methods
    @Override
    public void registerProduct(ProductRegisterRequestDto product) throws IOException {
        Product product1 = createProductWithCategoryAndUser(product);
        saveProductAndImages(product1, product);
    }

    @Override
    public List<ProductOverViewResponse> searchProductWithTitle(String query) {
        return productCrudService.getProductOverViewByQuery(query);
    }

    @Override
    public Product createProductWithCategoryAndUser(ProductRegisterRequestDto productRegisterRequestDto) {
        return productFactory.createWithRegisterRequestDto(productRegisterRequestDto);
    }

    @Override
    public void saveProductAndImages(Product product, ProductRegisterRequestDto productRequest){
        productCrudService.saveProduct(product);
        imageService.registerImageWithProduct(productRequest, product);
    }
    @Override
    public ProductDetailResponseDto getProductDetail(Long productId) {
        ProductDto product = getProductById(productId);
        ProductImageDtoList imageList = getProductImagesByProductId(productId);
        List<ReviewWithAvgProjection> reviewWithAvg = getProductReviewsByProductId(productId);
        return ProductDetailResponseDto.of(product, imageList, reviewWithAvg);
    }

    private ProductDto getProductById(Long productId) {
        return productCrudService.getProductDtoById(productId);
    }

    private ProductImageDtoList getProductImagesByProductId(Long productId) {
        return imageService.getImageByProductId(productId);
    }

    private List<ReviewWithAvgProjection> getProductReviewsByProductId(Long productId) {
        return reviewService.getAllReview(productId);
    }

}

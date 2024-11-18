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
    private final UserRepository userRepository;
    private final UserService userService;
    private final ProductCrudService productCrudService;
    private final ImageService imageService;
    private final ReviewService reviewService;
    private final CategoryBusinessService categoryBusinessService;

    // Public methods
    @Override
    public void registerProduct(ProductRegisterRequestDto product) throws IOException {
        Product product1 = createProduct(product);
        saveProductAndImages(product1, product);
    }

    @Override
    public ProductDetailResponseDto getProductDetail(Long productId) {
        ProductDto product = productCrudService.findProductDtoById(productId);
        ProductImageDtoList imageList = imageService.getImageByProductId(productId);
        List<ReviewWithAvgProjection> reviewWithAvg = reviewService.bringAllReview(productId);
        return ProductDetailResponseDto.of(product, imageList, reviewWithAvg);
    }

    @Override
    public List<ProductOverViewResponse> searchProductWithTitle(String query) {
        return productCrudService.findProductOverViewByQuery(query);
    }

    @Override
    public Product processProductRegisterData(ProductRegisterRequestDto product) {
        User user = resolveUser();
        CategoryInProductDto categoryInProduct = categoryBusinessService.resolveCategories(product);
        ProductRegisterDto productRegisterDto = ProductRegisterDto.of(product, categoryInProduct, user);
        return ProductFactory.createWithRegisterRequestDto(productRegisterDto);
    }

    @Override
    public User resolveUser() {
        userService.getAuthenticatedUser();
        User user = User.builder()
                .email("1")
                .phoneNumber("2")
                .nickname("z")
                .password("2")
                .build();
        userRepository.save(user);
        return user;
    }

    @Override
    public ProductRegisterDto createProductRegisterDto() {
        return null;
    }

    @Override
    public Product createProduct(ProductRegisterRequestDto product) {
        User user = resolveUser(); // 유저 조회
        CategoryInProductDto categoryInProduct = categoryBusinessService.resolveCategories(product); //카테고리 조회
        ProductRegisterDto productRegisterDto = ProductRegisterDto.of(product, categoryInProduct, user);
        return ProductFactory.createWithRegisterRequestDto(productRegisterDto);
    }

    @Override
    public void saveProductAndImages(Product product, ProductRegisterRequestDto productRequest){
        productCrudService.saveProduct(product);
        imageService.registerImageWithProduct(productRequest, product);
    }
}

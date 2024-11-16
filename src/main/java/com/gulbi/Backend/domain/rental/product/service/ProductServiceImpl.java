package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.ProductImageDto;
import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductRegisterDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductRegisterDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.request.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.response.ProductDetailResponseDto;
import com.gulbi.Backend.domain.rental.product.entity.Image;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.factory.ProductFactory;
import com.gulbi.Backend.domain.rental.product.repository.ProductRepository;
import com.gulbi.Backend.domain.rental.product.service.category.CategoryBusinessService;
import com.gulbi.Backend.domain.rental.product.vo.MainImage;
import com.gulbi.Backend.domain.rental.product.vo.ProductImageDtoList;
import com.gulbi.Backend.domain.rental.review.dto.ReviewWithAvgProjection;
import com.gulbi.Backend.domain.rental.review.service.ReviewService;
import com.gulbi.Backend.domain.user.entity.User;
import com.gulbi.Backend.domain.user.repository.UserRepository;
import com.gulbi.Backend.global.util.Base64Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final UserRepository userRepository; // user관련 태호가 머지해주기 전에는 임시로 만들어서 쓸거임. 추후 머지 되면 없어짐.
    private final ProductCrudService productCrudService;
    private final ImageService imageService;
    private final ReviewService reviewService;
    private final CategoryBusinessService categoryBusinessService;


    @Override
    public void registerProduct(ProductRegisterRequestDto product, List<MultipartFile> images) throws IOException {

        User user = User.builder().email("1").phoneNumber("2").nickname("z").password("2").build();  // user관련 태호가 머지해주기 전에는 임시로 만들어서 쓸거임. 추후 머지 되면 없어짐.
        userRepository.save(user); // user관련 태호가 머지해주기 전에는 임시로 만들어서 쓸거임. 추후 머지 되면 없어짐.

        CategoryInProductRegisterDto categoryInProductRegisterDto = categoryBusinessService.resolveCategories(product);
        ProductRegisterDto productRegisterDto = product.createProductDto();
        MainImage mainImg = MainImage.of(Base64Util.MultipartFileToString(images.get(0)));
        Product product1 = ProductFactory.createProduct(productRegisterDto, categoryInProductRegisterDto, user , mainImg);
        productCrudService.saveProduct(product1);
        imageService.registerImageWithProduct(images, product1);

    }

    @Override
    public ProductDetailResponseDto getProductDetail(Long productId) {
        ProductDto product = productCrudService.findProductById(productId);
        ProductImageDtoList imageList = imageService.getImageByProductId(productId);
        List<ReviewWithAvgProjection> reviewWithAvg = reviewService.bringAllReview(productId);
        return ProductDetailResponseDto.of(product,imageList,reviewWithAvg);
    }

    @Override
    public List<ProductOverViewResponse> searchProductWithTitle(String query) {
        return productCrudService.findProductOverViewByQuery(query);
    }


}

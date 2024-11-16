package com.gulbi.Backend.domain.rental.product.service;

import com.gulbi.Backend.domain.rental.product.dto.category.CategoryInProductRegisterDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductRegisterDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductResponseProjection;
import com.gulbi.Backend.domain.rental.product.dto.request.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.response.ProductDetailResponseDto;
import com.gulbi.Backend.domain.rental.product.entity.Image;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.factory.ProductFactory;
import com.gulbi.Backend.domain.rental.product.repository.ProductRepository;
import com.gulbi.Backend.domain.rental.product.service.category.CategoryBusinessService;
import com.gulbi.Backend.domain.rental.product.vo.MainImage;
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
    private final ProductRepository productRepository;
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
        productRepository.save(product1);
        imageService.registerImageWithProduct(images, product1);



    }

    @Override
    public ProductDetailResponseDto getProductDetail(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException());
        final String tag = product.getTag();
        final String title = product.getTitle();
        final String productName = product.getName();
        final String price = String.valueOf(product.getPrice());
        final String views = String.valueOf(product.getViews());
        final String sido = product.getSido();
        final String sigungu = product.getSigungu();
        final String bname = product.getBname();
        final String description = product.getDescription();
        final String rating = String.valueOf(product.getRating());
        final String bCategory= product.getBCategory().getName();
        final String mcategory = product.getMCategory().getName();
        final String scategory = product.getSCategory().getName();
        final String created_at = String.valueOf(product.getCreatedAt());

        List<Image> imageList = imageService.getImageByProductId(productId);
        final List<String> images = new ArrayList<>();
        for (Image image: imageList) {
            images.add(image.getUrl());
        }

        List<ReviewWithAvgProjection>reviewWithAvg =reviewService.bringAllReview(productId);
        System.out.println(reviewWithAvg.get(0).getContent());


        ProductDetailResponseDto response = ProductDetailResponseDto.builder()
                .tag(tag)
                .title(title)
                .productName(productName)
                .price(price)
                .view(views)
                .sido(sido)
                .sigungu(sigungu)
                .bname(bname)
                .description(description)
                .rating(rating)
                .bcategory(bCategory)
                .mcategory(mcategory)
                .scategory(scategory)
                .created_at(created_at)
                .images(images)
                .reviews(reviewWithAvg)
                .build();


    return  response;
    }

    @Override
    public List<ProductResponseProjection> searchProductWithTitle(String query) {
       List<ProductResponseProjection> products = productRepository.findProductByQuery(query);
        return products;
    }


}

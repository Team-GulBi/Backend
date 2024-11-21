package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.dto.product.ProductDto;
import com.gulbi.Backend.domain.rental.product.dto.product.ProductOverViewResponse;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductRegisterRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductSearchRequestDto;
import com.gulbi.Backend.domain.rental.product.dto.product.response.ProductDetailResponseDto;
import com.gulbi.Backend.domain.rental.product.entity.Product;
import com.gulbi.Backend.domain.rental.product.factory.ProductFactory;
import com.gulbi.Backend.domain.rental.product.service.image.ImageService;
import com.gulbi.Backend.domain.rental.product.dto.ProductImageDtoCollection;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrl;
import com.gulbi.Backend.domain.rental.product.vo.image.ImageUrlCollection;
import com.gulbi.Backend.domain.rental.review.dto.ReviewWithAvgProjection;
import com.gulbi.Backend.domain.rental.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDetailService productDetailService;
    private final ProductRegistrationService productRegistrationService;
    private final ProductSearchService productSearchService;

    @Override
    public List<ProductOverViewResponse> searchProductOverview(ProductSearchRequestDto productSearchRequestDto) {
        return productSearchService.searchProductByQuery(productSearchRequestDto);
    }
    @Override
    public void registrationProduct(ProductRegisterRequestDto productRegisterRequestDto){
        productRegistrationService.registerProduct(productRegisterRequestDto);
    }
    @Override
    public ProductDetailResponseDto getProductDetail(Long productId){
        return productDetailService.getProductDetail(productId);
    }

}

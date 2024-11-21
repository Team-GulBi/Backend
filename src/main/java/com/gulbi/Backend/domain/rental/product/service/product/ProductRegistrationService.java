package com.gulbi.Backend.domain.rental.product.service.product;

import com.gulbi.Backend.domain.rental.product.dto.product.request.ProductRegisterRequestDto;

public interface ProductRegistrationService {
    public void registerProduct(ProductRegisterRequestDto productRegisterRequestDto);
}

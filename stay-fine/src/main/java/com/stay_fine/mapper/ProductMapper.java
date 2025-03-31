package com.stay_fine.mapper;

import com.stay_fine.controller.request.ProductRequest;
import com.stay_fine.controller.response.ProductResponse;
import com.stay_fine.enums.ProductStatus;
import com.stay_fine.model.entity.Product;

public class ProductMapper {

    public static Product productRequestToEntity(ProductRequest request) {
        return Product.builder()
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }

    public static Product productResponseToEntity(ProductResponse productResponse) {
        return Product.builder()
                .id(productResponse.getId())
                .description(productResponse.getDescription())
                .price(productResponse.getPrice())
                .status(ProductStatus.valueOf(productResponse.getStatus()))
                .registrationDate(productResponse.getRegisrationDate())
                .build();
    }

    public static ProductResponse productEntityToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .status(product.getStatus().name())
                .regisrationDate(product.getRegistrationDate())
                .updateDate(product.getUpdateDate())
                .build();
    }


}

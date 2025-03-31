package com.stay_fine.service;

import com.stay_fine.enums.Error;
import com.stay_fine.enums.ProductStatus;
import com.stay_fine.exception.NotFoundException;
import com.stay_fine.model.entity.Product;
import com.stay_fine.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product create(Product product) {
        Product builderProduct = Product.builder()
                .description(product.getDescription())
                .price(product.getPrice())
                .status(product.getStatus())
                .build();

        return productRepository.save(builderProduct);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));
    }

    public Product update(Long id, Product product) {
        Product existProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));

        existProduct.setUpdateDate(LocalDateTime.now());
        productRequestToProductDB(product, existProduct);
        return productRepository.save(existProduct);
    }


    public void delete(Long id) {
        Product existsProduct = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Error.NOT_FOUND_EXCEPTION, id));

        existsProduct.setStatus(ProductStatus.EXCLUIDO);
        existsProduct.setUpdateDate(LocalDateTime.now());
        productRepository.save(existsProduct);
    }

    private void productRequestToProductDB(Product product, Product existProduct) {
        if (product.getDescription() != null && !Objects.equals(product.getDescription(), existProduct.getDescription())) {
            existProduct.setDescription(product.getDescription());
        }

        if (product.getPrice() != null && !Objects.equals(product.getPrice(), existProduct.getPrice())) {
            existProduct.setPrice(product.getPrice());
        }
    }
}

package com.stay_fine.controller;


import com.stay_fine.controller.request.ProductRequest;
import com.stay_fine.controller.response.ProductResponse;
import com.stay_fine.mapper.ProductMapper;
import com.stay_fine.service.ProductService;
import com.stay_fine.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody @Validated ProductRequest request) {
        Product savedProduct = productService.create(ProductMapper.productRequestToEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.productEntityToResponse(savedProduct));
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(Pageable pageable) {
        Page<Product> products = productService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(products.map(ProductMapper::productEntityToResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
        Product product = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.productEntityToResponse(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest request) {
        Product product = productService.update(id, ProductMapper.productRequestToEntity(request));
        return ResponseEntity.status(HttpStatus.OK).body(ProductMapper.productEntityToResponse(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

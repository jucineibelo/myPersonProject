package com.stay_fine.repository;

import com.stay_fine.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.id = :id AND p.status = 'ATIVO' ")
    Product findByIdActiveProduct(Long id);
}

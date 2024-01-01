package com.erpak.barter.repository;

import com.erpak.barter.enums.ProductStatus;
import com.erpak.barter.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByUserCity(String city);

    List<Product> findByBrandName(String brandName);

    List<Product> findByCategoryName(String categoryName);

    List<Product> findByUserId(int userId);

    List<Product> findByStatuses(ProductStatus status);
}

package com.erpak.barter.repository;


import com.erpak.barter.dto.ProductDTO;
import com.erpak.barter.enums.ProductStatus;
import com.erpak.barter.model.Brand;
import com.erpak.barter.model.Category;
import com.erpak.barter.model.Product;
import com.erpak.barter.model.User;
import com.erpak.barter.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product productOne;
    private Product productTwo;
    private User user;
    private Brand brand;
    private Category category;

    @BeforeEach
    public void init() {
        user = User.builder()
                .id(1)
                .city("Ankara")
                .build();
        brand = Brand.builder()
                .id(1)
                .name("Apple")
                .build();
        category = Category.builder()
                .id(1)
                .name("Technology")
                .build();
        productOne = Product.builder()
                .id(1)
                .name("PC")
                .barterPoint(500L)
                .description("Amazing PC")
                .statuses(Set.of(ProductStatus.NEW,ProductStatus.EXCELLENT))
                .user(user)
                .brand(brand)
                .category(category)
                .build();
        productTwo = Product.builder()
                .id(2)
                .name("TV")
                .barterPoint(200L)
                .description("Cheap TV")
                .statuses(Set.of(ProductStatus.USED,ProductStatus.NEW))
                .user(user)
                .brand(brand)
                .category(category)
                .build();
    }

    @Test
    public void testFindProductsByUserCity() throws Exception {

        List<Product> expectedProducts = Arrays.asList(productOne,productTwo);

        when(productRepository.findByUserCity("Ankara")).thenReturn(expectedProducts);

        ResponseEntity<?> responseEntity = productService.getProductsByCity("Ankara");

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expectedProducts.size(), ((List<ProductDTO>) responseEntity.getBody()).size());

    }

    @Test
    public void testFindProductsByBrandName() throws Exception {

        List<Product> expectedProducts = Arrays.asList(productOne,productTwo);

        when(productRepository.findByBrandName("Apple")).thenReturn(expectedProducts);

        ResponseEntity<?> responseEntity = productService.getProductsByBrandName("Apple");

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expectedProducts.size(),((List<ProductDTO>) responseEntity.getBody()).size());
    }

    @Test
    public void testFindProductsByCategoryName() throws Exception {

        List<Product> expectedProducts = Arrays.asList(productOne,productTwo);

        when(productRepository.findByCategoryName("Technology")).thenReturn(expectedProducts);

        ResponseEntity<?> responseEntity = productService.getProductsByCategoryName("Technology");

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expectedProducts.size(), ((List<ProductDTO>) responseEntity.getBody()).size());
    }


    @Test
    public void testFindProductsByUserId() {

        List<Product> expectedProducts = Arrays.asList(productOne,productTwo);

        when(productRepository.findByUserId(1)).thenReturn(expectedProducts);

        ResponseEntity<?> responseEntity = productService.getProductsByUserId(1);

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expectedProducts.size(), ((List<ProductDTO>) responseEntity.getBody()).size());
    }

    @Test
    public void testFindProductsByStatus() {

        List<Product> expectedProducts = Arrays.asList(productOne,productTwo);

        when(productRepository.findByStatuses(ProductStatus.NEW)).thenReturn(expectedProducts);

        ResponseEntity<?> responseEntity = productService.getProductsByStatus(ProductStatus.NEW);

        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expectedProducts.size(), ((List<ProductDTO>) responseEntity.getBody()).size());

    }


}

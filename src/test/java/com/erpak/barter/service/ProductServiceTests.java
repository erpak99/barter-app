package com.erpak.barter.service;

import com.erpak.barter.dto.ProductCreateRequest;
import com.erpak.barter.enums.ProductStatus;
import com.erpak.barter.exceptions.ExceptionMessages;
import com.erpak.barter.exceptions.ProductNotFoundException;
import com.erpak.barter.model.Brand;
import com.erpak.barter.model.Category;
import com.erpak.barter.model.Product;
import com.erpak.barter.model.User;
import com.erpak.barter.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private BrandService brandService;
    @Mock
    private UserService userService;
    @Mock
    private CategoryService categoryService;

    private Product productOne;
    private Product productTwo;
    private User user;
    private Brand brand;
    private Category category;

    @BeforeEach
    public void init() {
        user = User.builder().id(1).build();
        brand = Brand.builder().id(1).build();
        category = Category.builder().id(1).build();
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
                .statuses(Set.of(ProductStatus.USED))
                .user(user)
                .brand(brand)
                .category(category)
                .build();
    }

    @Test
    public void testCreateProduct() {

        ProductCreateRequest request = new ProductCreateRequest();
        request.setName("Smart Phone");
        request.setUserId(1);
        request.setBrandId(1);
        request.setCategoryId(1);

        ResponseEntity<String> response = productService.createProduct(request);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals("Product created successfully", response.getBody());

        verify(productRepository, times(1)).save(any());

    }

    @Test
    public void testFindAllProducts() {

        List<Product> productList = Arrays.asList(productOne,productTwo);
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.findAll();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(2, result.size())
        );



    }

    @Test
    public void testFindProductById() {

        when(productRepository.findById(1)).thenReturn(Optional.of(productOne));

        Product result = productService.findById(1);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1,result.getId())

        );

    }

    @Test
    public void testFindProductFindByIdNotFound() {

        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> {
                    Product product = productService.findById(1);
                },
                ExceptionMessages.PRODUCT_NOT_FOUND
        );

    }


}

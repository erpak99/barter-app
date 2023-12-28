package com.erpak.barter.service;

import com.erpak.barter.dto.ProductCreateRequest;
import com.erpak.barter.dto.ProductDTO;
import com.erpak.barter.model.Brand;
import com.erpak.barter.model.Category;
import com.erpak.barter.model.Product;
import com.erpak.barter.model.User;
import com.erpak.barter.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandService brandService;
    private final CategoryService categoryService;
    private final UserService userService;

    public ResponseEntity<String> createProduct(ProductCreateRequest request) {

        Brand brand = brandService.findById(request.getBrandId());
        Category category = categoryService.findById(request.getCategoryId());
        User user = userService.findById(request.getUserId());

        Product product = Product.builder()
                .name(request.getName())
                .barterPoint(request.getBarterPoint())
                .description(request.getDescription())
                .statuses(request.getStatuses())
                .user(user)
                .brand(brand)
                .category(category)
                .build();

        productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");

    }

    public Product findById(int id) {
        return productRepository.findById(id).orElseThrow();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}

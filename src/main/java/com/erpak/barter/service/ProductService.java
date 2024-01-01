package com.erpak.barter.service;

import com.erpak.barter.dto.ProductCreateRequest;
import com.erpak.barter.dto.ProductDTO;
import com.erpak.barter.dto.ProductUpdateRequest;
import com.erpak.barter.enums.ProductStatus;
import com.erpak.barter.exceptions.ExceptionMessages;
import com.erpak.barter.exceptions.ProductNotFoundException;
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
import java.util.Optional;

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
        return productRepository.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException
                                (ExceptionMessages.PRODUCT_NOT_FOUND + " with id " + id));
    }


    public List<Product> findAll() {

        return productRepository.findAll();

    }


    public ResponseEntity<?> getProductsByCity(String city) throws Exception {
        List<ProductDTO> products = productRepository.findByUserCity(city)
                .stream()
                .map(product -> new ProductDTO(product))
                .toList();

        if (products.isEmpty()) {
            throw new ProductNotFoundException
                    (ExceptionMessages.PRODUCT_NOT_FOUND + " in the city of " + city);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    public ResponseEntity<?> getProductsByBrandName(String brandName) throws Exception {
        List<ProductDTO> products = productRepository.findByBrandName(brandName)
                .stream().map(product -> new ProductDTO(product))
                .toList();
        if(products.isEmpty()) {
            throw new ProductNotFoundException
                    (ExceptionMessages.PRODUCT_NOT_FOUND + " in " + brandName + " brand");
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<?> getProductsByCategoryName(String categoryName) {
        List<ProductDTO> products = productRepository.findByCategoryName(categoryName)
                .stream().map(product -> new ProductDTO(product))
                .toList();
        if(products.isEmpty()) {
            throw new ProductNotFoundException
                    (ExceptionMessages.PRODUCT_NOT_FOUND + " in " + categoryName + " category");
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<?> getProductsByUserId(int userId) {

        List<ProductDTO> products = productRepository.findByUserId(userId)
                .stream()
                .map(product -> new ProductDTO(product))
                .toList();

        if (products.isEmpty()) {
            throw new ProductNotFoundException
                    (ExceptionMessages.PRODUCT_NOT_FOUND + " for the user with id " + userId);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<?> getProductsByStatus(ProductStatus status) {

        List<ProductDTO> products = productRepository.findByStatuses(status)
                .stream()
                .map(product -> new ProductDTO(product))
                .toList();

        if (products.isEmpty()) {
            throw new ProductNotFoundException
                    (ExceptionMessages.PRODUCT_NOT_FOUND + " for the status " + status);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    public ResponseEntity<String> updateProduct(int productId, ProductUpdateRequest request) {

        Optional<Product> product = productRepository.findById(productId);
        User user = userService.findById(request.getUserId());
        Category category = categoryService.findById(request.getCategoryId());
        Brand brand = brandService.findById(request.getBrandId());

        if(product.isPresent()) {
            Product updatedProduct = product.get();
            updatedProduct.setName(request.getName());
            updatedProduct.setBarterPoint(request.getBarterPoint());
            updatedProduct.setDescription(request.getDescription());
            updatedProduct.setStatuses(request.getStatuses());
            updatedProduct.setUser(user);
            updatedProduct.setCategory(category);
            updatedProduct.setBrand(brand);
            productRepository.save(updatedProduct);
            return ResponseEntity.status(HttpStatus.OK).body("Product updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product can not be found");
        }
    }
}

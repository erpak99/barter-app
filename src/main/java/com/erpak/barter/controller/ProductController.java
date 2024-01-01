package com.erpak.barter.controller;

import com.erpak.barter.dto.ProductCreateRequest;
import com.erpak.barter.dto.ProductDTO;
import com.erpak.barter.enums.ProductStatus;
import com.erpak.barter.model.Product;
import com.erpak.barter.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createProduct(
                            @RequestBody @Valid ProductCreateRequest request) {
        return productService.createProduct(request);
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable int id) {
        Product product = productService.findById(id);
        return new ProductDTO(product);
    }

    @GetMapping
    public List<ProductDTO> findAll() {
        return productService.findAll()
                .stream()
                .map(product -> new ProductDTO(product))
                .toList();

    }

    @GetMapping("/byCity/{city}")
    public ResponseEntity<?> getProductsByCity(@PathVariable String city) throws Exception {
        return productService.getProductsByCity(city);

    }

    @GetMapping("/byBrandName/{brandName}")
    public ResponseEntity<?> getProductsByBrandName(
                                    @PathVariable String brandName)
                                    throws Exception {
        return productService.getProductsByBrandName(brandName);

    }


    @GetMapping("/byCategoryName/{categoryName}")
    public ResponseEntity<?> getProductsByCategoryName(
            @PathVariable String categoryName)
            throws Exception {
        return productService.getProductsByCategoryName(categoryName);

    }

    @GetMapping("/byUserId/{userId}")
    public ResponseEntity<?> getProductsByUserId(@PathVariable int userId) {
        return productService.getProductsByUserId(userId);

    }

    @GetMapping("/byStatus")
    public ResponseEntity<?> getProductsByStatus(@RequestParam ProductStatus status) {
        return productService.getProductsByStatus(status);
    }


}

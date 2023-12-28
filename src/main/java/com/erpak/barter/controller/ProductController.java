package com.erpak.barter.controller;

import com.erpak.barter.dto.ProductCreateRequest;
import com.erpak.barter.dto.ProductDTO;
import com.erpak.barter.model.Product;
import com.erpak.barter.service.ProductService;
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
    public ResponseEntity<String> createProduct(@RequestBody ProductCreateRequest request) {
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
                .map(user -> new ProductDTO(user))
                .toList();

    }

}

package com.erpak.barter.service;

import com.erpak.barter.dto.BrandCreateRequest;
import com.erpak.barter.model.Brand;
import com.erpak.barter.model.Category;
import com.erpak.barter.repository.BrandRepository;
import com.erpak.barter.rules.BrandCreateRules;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final CategoryService categoryService;
    private final BrandCreateRules brandCreateRules;

    public ResponseEntity<String> createBrand(BrandCreateRequest request) {

        brandCreateRules.checkIfBrandNameExists(request.getName());

        Category category = categoryService.findById(request.getCategoryId());

        Brand brand = Brand.builder()
                .name(request.getName())
                .category(category)
                .build();

        brandRepository.save(brand);
        return ResponseEntity.status(HttpStatus.CREATED).body("Brand created successfully");
    }

    public Brand findById(int id) {

        return brandRepository.findById(id).orElseThrow();
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }
}

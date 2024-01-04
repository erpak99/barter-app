package com.erpak.barter.service;

import com.erpak.barter.dto.BrandCreateRequest;
import com.erpak.barter.dto.BrandUpdateRequest;
import com.erpak.barter.exceptions.BrandNotFoundException;
import com.erpak.barter.exceptions.ExceptionMessages;
import com.erpak.barter.model.Brand;
import com.erpak.barter.model.Category;
import com.erpak.barter.repository.BrandRepository;
import com.erpak.barter.rules.BrandCreateRules;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        return brandRepository.findById(id).orElseThrow(
                () -> new BrandNotFoundException(ExceptionMessages.BRAND_NOT_FOUND)
        );
    }

    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    public ResponseEntity<String> updateBrand(int brandId, BrandUpdateRequest request) {

        Optional<Brand> brand = brandRepository.findById(brandId);
        Category category = categoryService.findById(request.getCategoryId());

        if(brand.isPresent()) {
            Brand updatedBrand = brand.get();
            updatedBrand.setName(request.getName());
            updatedBrand.setCategory(category);
            brandRepository.save(updatedBrand);
            return ResponseEntity.status(HttpStatus.OK).body("Brand updated successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Brand can not be found");
        }
    }
}

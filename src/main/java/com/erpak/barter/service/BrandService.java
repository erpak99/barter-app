package com.erpak.barter.service;

import com.erpak.barter.dto.BrandCreateRequest;
import com.erpak.barter.model.Brand;
import com.erpak.barter.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public ResponseEntity<String> createBrand(BrandCreateRequest request) {

        Brand brand = Brand.builder()
                .name(request.getName())
                .build();

        brandRepository.save(brand);
        return ResponseEntity.status(HttpStatus.CREATED).body("Brand created successfully");
    }
}

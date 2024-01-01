package com.erpak.barter.controller;

import com.erpak.barter.dto.BrandCreateRequest;
import com.erpak.barter.dto.BrandDTO;
import com.erpak.barter.dto.BrandProductDTO;
import com.erpak.barter.dto.BrandUpdateRequest;
import com.erpak.barter.model.Brand;
import com.erpak.barter.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createBrand(
                                @RequestBody @Valid BrandCreateRequest request) {

        return brandService.createBrand(request);
    }

    @GetMapping("/{id}")
    public BrandProductDTO findById(@PathVariable int id) {
        Brand brand = brandService.findById(id);
        return new BrandProductDTO(brand);
    }

    @GetMapping
    public List<BrandDTO> findAll() {
        return brandService.findAll()
                .stream()
                .map(brand -> new BrandDTO(brand))
                .toList();
    }


    @PutMapping("/{brandId}")
    public ResponseEntity<String> updateBrand(@PathVariable int brandId,
                                              @RequestBody @Valid BrandUpdateRequest request) {
        return brandService.updateBrand(brandId,request);
    }

}

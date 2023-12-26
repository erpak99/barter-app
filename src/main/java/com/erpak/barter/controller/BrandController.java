package com.erpak.barter.controller;

import com.erpak.barter.dto.BrandCreateRequest;
import com.erpak.barter.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/brands")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createBrand(@RequestBody BrandCreateRequest request) {

        return brandService.createBrand(request);
    }


}

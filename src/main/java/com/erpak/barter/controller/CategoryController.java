package com.erpak.barter.controller;

import com.erpak.barter.dto.CategoryCreateRequest;
import com.erpak.barter.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> createCategory(
                                @RequestBody CategoryCreateRequest request) {
        return categoryService.createCategory(request);
    }

}

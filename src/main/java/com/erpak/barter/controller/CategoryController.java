package com.erpak.barter.controller;

import com.erpak.barter.dto.CategoryCreateRequest;
import com.erpak.barter.dto.CategoryDTO;
import com.erpak.barter.dto.CategoryProductDTO;
import com.erpak.barter.dto.CategoryUpdateRequest;
import com.erpak.barter.model.Category;
import com.erpak.barter.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> createCategory(
                                @RequestBody @Valid CategoryCreateRequest request) {
        return categoryService.createCategory(request);
    }

    @GetMapping("/{id}")
    public CategoryProductDTO findById(@PathVariable int id) {
        Category category = categoryService.findById(id);
        return new CategoryProductDTO(category);

    }

    @GetMapping
    public List<CategoryDTO> findAll(){
        return categoryService.findAll()
                .stream()
                .map(category -> new CategoryDTO(category))
                .toList();
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable int categoryId,
                            @RequestBody @Valid CategoryUpdateRequest request) {
        return categoryService.updateCategory(categoryId,request);

    }

}

package com.erpak.barter.service;

import com.erpak.barter.dto.CategoryCreateRequest;
import com.erpak.barter.dto.CategoryDTO;
import com.erpak.barter.model.Category;
import com.erpak.barter.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ResponseEntity<String> createCategory(CategoryCreateRequest request) {

        Category category = Category.builder()
                        .name(request.getName())
                        .build();

        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category created successfully");

    }

    public Category findById(int id) {
        return categoryRepository.findById(id).orElseThrow();

    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}

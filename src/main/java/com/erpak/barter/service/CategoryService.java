package com.erpak.barter.service;

import com.erpak.barter.dto.CategoryCreateRequest;
import com.erpak.barter.dto.CategoryUpdateRequest;
import com.erpak.barter.exceptions.CategoryNotFoundException;
import com.erpak.barter.exceptions.ExceptionMessages;
import com.erpak.barter.model.Category;
import com.erpak.barter.repository.CategoryRepository;
import com.erpak.barter.rules.CategoryCreateRules;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryCreateRules categoryCreateRules;

    public ResponseEntity<String> createCategory(CategoryCreateRequest request) {

        categoryCreateRules.checkIfCategoryNameExists(request.getName());

        Category category = Category.builder()
                .name(request.getName())
                .build();

        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body("Category created successfully");

    }


    public Category findById(int id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException(ExceptionMessages.CATEGORY_NOT_FOUND)
        );

    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public ResponseEntity<String> updateCategory(int categoryId,
                                                 CategoryUpdateRequest request) {

        Optional<Category> category = categoryRepository.findById(categoryId);

        if (category.isPresent()) {
            Category updatedCategory = category.get();

            updatedCategory.setName(request.getName());

            categoryRepository.save(updatedCategory);
            return ResponseEntity.status(HttpStatus.OK).body("Category updated successfully");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category can not be found");
        }
    }


}
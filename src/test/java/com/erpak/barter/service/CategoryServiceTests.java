package com.erpak.barter.service;

import com.erpak.barter.dto.CategoryCreateRequest;
import com.erpak.barter.dto.CategoryUpdateRequest;
import com.erpak.barter.exceptions.ExceptionMessages;
import com.erpak.barter.model.Category;
import com.erpak.barter.repository.CategoryRepository;
import com.erpak.barter.rules.CategoryCreateRules;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryCreateRules categoryCreateRules;

    @InjectMocks
    CategoryService categoryService;

    private Category categoryOne;
    private Category categoryTwo;

    @BeforeEach
    public void init() {
        categoryOne = Category.builder().id(1).name("Category 1").build();
        categoryTwo = Category.builder().id(2).name("Category 2").build();
    }

    @Test
    public void testCreateCategory() {

        CategoryCreateRequest request = new CategoryCreateRequest();
        request.setName("Test Category");

        Mockito.doNothing().when(categoryCreateRules).checkIfCategoryNameExists("Test Category");

        ResponseEntity<String> response = categoryService.createCategory(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Category created successfully", response.getBody());

        verify(categoryRepository, times(1)).save(any());
    }


    @Test
    public void testFindAllCategories() {

        List<Category> categoryList = Arrays.asList(categoryOne, categoryTwo);

        when(categoryRepository.findAll()).thenReturn(categoryList);

        List<Category> result = categoryService.findAll();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(2, result.size()),
                () -> assertEquals("Category 1", result.get(0).getName()),
                () -> assertEquals("Category 2", result.get(1).getName())
        );
    }


    @Test
    public void testFindCategoryById() {

        when(categoryRepository.findById(1)).thenReturn(Optional.of(categoryOne));

        Category result = categoryService.findById(1);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1, result.getId()),
                () -> assertEquals("Category 1", result.getName())
        );

    }

    @Test
    public void testFindCategoryByIdNotFound() {

        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class,
                () -> {
                        Category category = categoryService.findById(1);
                }, ExceptionMessages.CATEGORY_NOT_FOUND
                );
    }


    @Test
    public void testUpdateCategory() {

        when(categoryRepository.findById(1)).thenReturn(Optional.of(categoryOne));

        CategoryUpdateRequest updateRequest = new CategoryUpdateRequest();
        updateRequest.setName("New Category");
        ResponseEntity<String> response = categoryService.updateCategory(1, updateRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Category updated successfully", response.getBody());

        assertEquals(updateRequest.getName(), categoryOne.getName());
        verify(categoryRepository, times(1)).save(categoryOne);
    }


    @Test
    public void testUpdateCategoryNotFound() {

        when(categoryRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<String> response = categoryService.updateCategory(1, new CategoryUpdateRequest());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Category can not be found", response.getBody());

        verify(categoryRepository, never()).save(any(Category.class));
    }

}

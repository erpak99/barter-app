package com.erpak.barter.service;

import com.erpak.barter.dto.BrandCreateRequest;
import com.erpak.barter.dto.BrandUpdateRequest;
import com.erpak.barter.exceptions.BrandNotFoundException;
import com.erpak.barter.exceptions.ExceptionMessages;
import com.erpak.barter.model.Brand;
import com.erpak.barter.model.Category;
import com.erpak.barter.repository.BrandRepository;
import com.erpak.barter.rules.BrandCreateRules;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTests {

    @InjectMocks
    private BrandService brandService;
    @Mock
    private CategoryService categoryService;
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private BrandCreateRules brandCreateRules;

    private Brand brandOne;
    private Brand brandTwo;
    private Category category;

    @BeforeEach
    public void init() {
        category = Category.builder().id(1).name("Test category").build();
        brandOne = Brand.builder().id(1).name("Brand 1").category(category).build();
        brandTwo = Brand.builder().id(2).name("Brand 2").category(category).build();
    }


    @Test
    public void testCreateBrand() {

        BrandCreateRequest createRequest = new BrandCreateRequest();
        createRequest.setName("Test Brand");
        createRequest.setCategoryId(3);

        Mockito.doNothing().when(brandCreateRules).checkIfBrandNameExists("Test Brand");

        ResponseEntity<String> response = brandService.createBrand(createRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Brand created successfully", response.getBody());

        verify(brandRepository, times(1)).save(any());

    }

    @Test
    public void testFindAllBrands() {

        List<Brand> brandList = Arrays.asList(brandOne, brandTwo);

        when(brandRepository.findAll()).thenReturn(brandList);
        List<Brand> result = brandService.findAll();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(2, result.size()),
                () -> assertEquals("Brand 1",result.get(0).getName()),
                () -> assertEquals("Brand 2", result.get(1).getName())
        );
    }

    @Test
    public void testFindBrandById() {

        when(brandRepository.findById(1)).thenReturn(Optional.of(brandOne));

        Brand result = brandService.findById(1);

        assertAll(
                () -> assertNotNull(result),
                ()-> assertEquals(1,result.getId()),
                () -> assertEquals("Brand 1",result.getName())
        );

    }

    @Test
    public void testFindBrandByIdNotFound() {

        when(brandRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(
                    BrandNotFoundException.class,
                        () -> {
                            Brand brand = brandService.findById(1);
                        },
                ExceptionMessages.BRAND_NOT_FOUND
        );

    }

    @Test
    public void testUpdateBrand() {

        BrandUpdateRequest updateRequest = new BrandUpdateRequest();

        updateRequest.setName("Test Brand");
        updateRequest.setCategoryId(2);

        when(brandRepository.findById(1)).thenReturn(Optional.of(brandOne));
        when(categoryService.findById(updateRequest.getCategoryId())).thenReturn(new Category());

        ResponseEntity<String> response = brandService.updateBrand(1, updateRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Brand updated successfully", response.getBody());

        assertEquals(updateRequest.getName(),brandOne.getName());
        assertNotNull(brandOne.getCategory());

        verify(brandRepository, times(1)).save(brandOne);

    }


    @Test
    public void testUpdateBrandNotFound() {

        when(brandRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<String> response = brandService.updateBrand(1, new BrandUpdateRequest());

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals("Brand can not be found",response.getBody());

        verify(brandRepository, never()).save(any(Brand.class));
    }


}

package com.erpak.barter.dto;

import com.erpak.barter.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryProductDTO {

    private String name;
    private List<ProductCategoryDTO> products;

    public CategoryProductDTO(Category category) {
        this.name = category.getName();
        this.products = category.getProducts()
                .stream()
                .map(product -> new ProductCategoryDTO(product))
                .toList();

    }

}

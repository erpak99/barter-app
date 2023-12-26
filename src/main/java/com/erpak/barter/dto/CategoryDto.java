package com.erpak.barter.dto;

import com.erpak.barter.model.Category;
import lombok.Builder;
import lombok.Data;

@Data
public class CategoryDto {
    private String name;

    public CategoryDto(Category category) {
        this.name = category.getName();
    }

}

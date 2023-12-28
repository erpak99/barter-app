package com.erpak.barter.dto;

import com.erpak.barter.model.Category;
import lombok.Builder;
import lombok.Data;

@Data
public class CategoryDTO {
    private String name;

    public CategoryDTO(Category category) {
        this.name = category.getName();
    }

}

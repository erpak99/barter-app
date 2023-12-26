package com.erpak.barter.dto;

import com.erpak.barter.model.Brand;
import lombok.Data;

@Data
public class BrandDto {

    private String name;

    public BrandDto(Brand brand) {
        this.name = brand.getName();
    }

}

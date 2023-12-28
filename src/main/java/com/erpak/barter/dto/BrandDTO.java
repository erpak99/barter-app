package com.erpak.barter.dto;

import com.erpak.barter.model.Brand;
import lombok.Data;

@Data
public class BrandDTO {

    private String name;


    public BrandDTO(Brand brand) {
        this.name = brand.getName();
    }

}

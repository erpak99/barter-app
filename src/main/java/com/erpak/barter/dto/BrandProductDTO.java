package com.erpak.barter.dto;

import com.erpak.barter.model.Brand;
import lombok.Data;

import java.util.List;

@Data
public class BrandProductDTO {

    private String name;
    private List<ProductBrandDTO> products;


    public BrandProductDTO(Brand brand) {
        this.name = brand.getName();
        this.products = brand.getProducts()
                .stream()
                .map(product -> new ProductBrandDTO(product))
                .toList();
    }

}

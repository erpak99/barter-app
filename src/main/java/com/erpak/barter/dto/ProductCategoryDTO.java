package com.erpak.barter.dto;

import com.erpak.barter.enums.ProductStatus;
import com.erpak.barter.model.Product;
import lombok.Data;

import java.util.Set;

@Data
public class ProductCategoryDTO {

    private String name;
    private Long barterPoint;
    private String userEmail;
    private Set<ProductStatus> statuses;
    private String brandName;

    public ProductCategoryDTO(Product product) {
        this.name = product.getName();
        this.barterPoint = product.getBarterPoint();
        this.userEmail = product.getUser().getEmail();
        this.statuses = product.getStatuses();
        this.brandName = product.getBrand().getName();
    }

}

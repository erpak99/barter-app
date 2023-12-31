package com.erpak.barter.dto;

import com.erpak.barter.enums.ProductStatus;
import com.erpak.barter.model.Product;
import lombok.Data;

import java.util.Set;

@Data
public class ProductBarterDTO {

    private String name;
    private Long barterPoint;
    private String description;
    private Set<ProductStatus> statuses;
    private String brandName;
    private String categoryName;

    public ProductBarterDTO(Product product) {
        this.name = product.getName();
        this.barterPoint = product.getBarterPoint();
        this.description = product.getDescription();
        this.statuses = product.getStatuses();
        this.brandName = product.getBrand().getName();
        this.categoryName = product.getCategory().getName();
    }


}

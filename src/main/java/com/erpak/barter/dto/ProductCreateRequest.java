package com.erpak.barter.dto;

import com.erpak.barter.enums.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest {

    private String name;
    private Long barterPoint;
    private String description;
    private Set<ProductStatus> statuses;
    private int userId;
    private int categoryId;
    private int brandId;

}

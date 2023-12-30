package com.erpak.barter.dto;

import com.erpak.barter.enums.ProductStatus;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Product name can not be empty.")
    private String name;
    private Long barterPoint;
    @NotBlank(message = "Description can not be empty.")
    private String description;
    private Set<ProductStatus> statuses;
    private int userId;
    private int categoryId;
    private int brandId;

}

package com.erpak.barter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandCreateRequest {

    @NotBlank(message = "Brand name can not be empty.")
    private String name;

    @NotNull(message = "Category type for the brand can not be empty.")
    private int categoryId;

}

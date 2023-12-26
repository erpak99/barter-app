package com.erpak.barter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateRequest {

    private String name;
    private Long barterPoint;
    private Long userId;
    private Long categoryId;
    private Long brandId;

}

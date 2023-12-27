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
    private int userId;
    private int categoryId;
    private int brandId;

}

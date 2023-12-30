package com.erpak.barter.dto;

import com.erpak.barter.model.Product;
import com.erpak.barter.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BarterCreateRequest {


    private User userOne;
    private Product productOne;
    private User userTwo;
    private Product productTwo;

}

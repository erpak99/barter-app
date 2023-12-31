package com.erpak.barter.dto;

import com.erpak.barter.enums.BarterStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BarterCreateRequest {

    private int userOneId;
    private int productOneId;
    private int userTwoId;
    private int productTwoId;
    private BarterStatus barterStatus;
}

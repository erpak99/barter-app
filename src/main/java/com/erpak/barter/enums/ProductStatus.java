package com.erpak.barter.enums;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public enum ProductStatus {

    NEW,
    LIKE_NEW,
    EXCELLENT,
    GOOD,
    FAIR,
    USED,
    WORN,
    DAMAGED,
    DEFECTIVE


}

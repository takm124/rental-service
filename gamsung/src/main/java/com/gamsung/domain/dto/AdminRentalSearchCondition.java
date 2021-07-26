package com.gamsung.domain.dto;

import lombok.Data;

@Data
public class AdminRentalSearchCondition {

    private String searchDate;
    private String rentalNum;
    private String staffName;
    private String orderType;
}

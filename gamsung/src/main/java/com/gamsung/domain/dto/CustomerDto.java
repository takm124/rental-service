package com.gamsung.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class CustomerDto {

    private String customerName;
    private String phoneNum;
    private String rentalNum;

    @QueryProjection
    public CustomerDto(String customerName, String phoneNum) {
        this.customerName = customerName;
        this.phoneNum = phoneNum;
    }

    public CustomerDto(String customerName, String phoneNum, String rentalNum) {
        this.customerName = customerName;
        this.phoneNum = phoneNum;
        this.rentalNum = rentalNum;
    }
}

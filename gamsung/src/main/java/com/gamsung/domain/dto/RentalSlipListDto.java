package com.gamsung.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.util.List;

@Data
@ToString
public class RentalSlipListDto {

    private String rentalNum;
    private Long customerCount;
    private int deposit;
    private String receiver;

    @QueryProjection
    public RentalSlipListDto(String rentalNum, Long customerCount, int deposit, String receiver) {
        this.rentalNum = rentalNum;
        this.customerCount = customerCount;
        this.deposit = deposit;
        this.receiver = receiver;
    }
}

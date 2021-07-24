package com.gamsung.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReturnSlipListDto {

    private String rentalNum;
    private Long customerCount;
    private int deposit;
    private String receiver;
    private String payment;

    @QueryProjection
    public ReturnSlipListDto(String rentalNum, Long customerCount, int deposit, String receiver, String payment) {
        this.rentalNum = rentalNum;
        this.customerCount = customerCount;
        this.deposit = deposit;
        this.receiver = receiver;
        this.payment = payment;
    }
}

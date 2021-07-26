package com.gamsung.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AdminRentalSlipListDto {

    private String rentalNum;
    private String receiver; // 접수받은 직원
    private String payment; // 대여 및 결제 직원
    private String gamsung_pos; // 감성포스 체크자
    private String returner; // 반납받은 직원
    private LocalDateTime enterDate; // 방문날짜 - rentalSlip의 createdDate로 사용


    @QueryProjection
    public AdminRentalSlipListDto(String rentalNum, String receiver, String payment, String gamsung_pos, String returner, LocalDateTime enterDate) {
        this.rentalNum = rentalNum;
        this.receiver = receiver;
        this.payment = payment;
        this.gamsung_pos = gamsung_pos;
        this.returner = returner;
        this.enterDate = enterDate;
    }
}

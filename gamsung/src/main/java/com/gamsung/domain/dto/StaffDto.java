package com.gamsung.domain.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class StaffDto {

    private String staffName;
    private String phoneNumber;

    @QueryProjection
    public StaffDto(String staffName, String phoneNumber) {
        this.staffName = staffName;
        this.phoneNumber = phoneNumber;
    }
}

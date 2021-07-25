package com.gamsung.domain.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewStaffDto {

    private String staffNum;
    private String staffName;
    private String phoneNum;
    private String loginId;
    private String password;

}

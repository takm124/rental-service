package com.gamsung.domain;

import com.gamsung.domain.dto.NewStaffDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Staff {

    @Id @GeneratedValue
    @Column(name = "staff_id")
    private Long id;

    private String staffNum;

    private String staffName;

    private String loginId;

    private String password;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Place place;

    @Enumerated(EnumType.STRING)
    private JobPosition jobPosition;

    public Staff(String staffNum, String staffName, String loginId, String password, String phoneNumber, Place place, JobPosition jobPosition) {
        this.staffNum = staffNum;
        this.staffName = staffName;
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.place = place;
        this.jobPosition = jobPosition;
    }

    public void editStaff(NewStaffDto dto) {
        this.staffName = dto.getStaffName();
        this.phoneNumber = dto.getPhoneNum();
        this.loginId = dto.getLoginId();
        this.password = dto.getPassword();
    }

}

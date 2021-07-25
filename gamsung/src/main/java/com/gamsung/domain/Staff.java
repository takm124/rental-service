package com.gamsung.domain;

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

    private String staffName;

    private String loginId;

    private String password;

    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Place place;

    @Enumerated(EnumType.STRING)
    private JobPosition jobPosition;

    public Staff(String staffName, String loginId, String password, Place place, JobPosition jobPosition) {
        this.staffName = staffName;
        this.loginId = loginId;
        this.password = password;
        this.place = place;
        this.jobPosition = jobPosition;
    }

}

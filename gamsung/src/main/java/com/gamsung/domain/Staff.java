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

    @Enumerated(EnumType.STRING)
    private Place place;

    public Staff(String staffName, String loginId, String password, Place place) {
        this.staffName = staffName;
        this.loginId = loginId;
        this.password = password;
        this.place = place;
    }
}

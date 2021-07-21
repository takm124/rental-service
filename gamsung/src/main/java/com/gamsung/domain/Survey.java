package com.gamsung.domain;

import com.gamsung.domain.dto.SurveyDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Survey {

    @Id @GeneratedValue
    @Column(name = "survey_id")
    private Long id;

    @OneToOne(mappedBy = "survey", fetch = FetchType.LAZY)
    private RentalSlip rentalSlip;

    private int age_10;
    private int age_20_25;
    private int age_26_30;
    private int age_31_35;
    private int age_36_40;
    private int age_40;

    private int search;
    private int friends;
    private int blog;
    private int youtube;
    private int instagram;
    private int facebook;

    private int seoul;
    private int incheon;
    private int gyeonggi;
    private int gangwon;
    private int chungcheong;
    private int jeolla;
    private int gyeongsang;
    private int jeju;
    private int abroad;

    public Survey(SurveyDto dto){
        this.age_10 = dto.getAge_10();
        this.age_20_25 = dto.getAge_20_25();
        this.age_26_30 = dto.getAge_26_30();
        this.age_31_35 = dto.getAge_31_35();
        this.age_36_40 = dto.getAge_36_40();
        this.age_40 = dto.getAge_40();

        this.search = dto.getSearch();
        this.friends = dto.getFriends();
        this.blog = dto.getBlog();
        this.youtube = dto.getYoutube();
        this.instagram = dto.getInstagram();
        this.facebook = dto.getFacebook();

        this.seoul = dto.getSeoul();
        this.incheon = dto.getIncheon();
        this.gyeonggi = dto.getGyeonggi();
        this.gangwon = dto.getGangwon();
        this.chungcheong = dto.getChungcheong();
        this.jeolla = dto.getJeolla();
        this.gyeongsang = dto.getGyeongsang();
        this.jeju = dto.getJeju();
        this.abroad = dto.getAbroad();
    }
}

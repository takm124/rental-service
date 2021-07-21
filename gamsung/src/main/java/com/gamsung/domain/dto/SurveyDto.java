package com.gamsung.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class SurveyDto {
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

}

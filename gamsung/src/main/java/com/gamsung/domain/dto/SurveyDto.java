package com.gamsung.domain.dto;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@ToString
public class SurveyDto {
    private Integer age_10;
    private Integer age_20_25;
    private Integer age_26_30;
    private Integer age_31_35;
    private Integer age_36_40;
    private Integer age_40;

    private Integer search;
    private Integer friends;
    private Integer blog;
    private Integer youtube;
    private Integer instagram;
    private Integer facebook;

    private Integer seoul;
    private Integer incheon;
    private Integer gyeonggi;
    private Integer gangwon;
    private Integer chungcheong;
    private Integer jeolla;
    private Integer gyeongsang;
    private Integer jeju;
    private Integer abroad;

}

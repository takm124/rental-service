package com.gamsung.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter
@NoArgsConstructor
public class MaleCloth {

    @Id @GeneratedValue
    @Column(name = "malecloth_id")
    private Long id;

    private Integer male_jacket;
    private Integer male_shirts;
    private Integer male_pants;
    private Integer male_vest;
    private Integer male_pullover;
    private Integer male_cardigan;
    private Integer male_tie;
    private Integer belt;

    @Builder
    public MaleCloth(Integer male_jacket, Integer male_shirts, Integer male_pants, Integer male_vest, Integer male_pullover, Integer male_cardigan,
                        Integer male_tie, Integer belt ){

        this.male_jacket = male_jacket;
        this.male_shirts = male_shirts;
        this.male_pants = male_pants;
        this.male_vest = male_vest;
        this.male_pullover = male_pullover;
        this.male_cardigan = male_cardigan;
        this.male_tie = male_tie;
        this.belt = belt;
    }


}

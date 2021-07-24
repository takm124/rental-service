package com.gamsung.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter
@NoArgsConstructor
public class FemaleCloth {

    @Id @GeneratedValue
    @Column(name = "femalecloth_id")
    private Long id;

    private Integer female_jacket;
    private Integer female_shirts;
    private Integer female_pants;
    private Integer female_vest;
    private Integer female_pullover;
    private Integer female_cardigan;
    private Integer female_tie;

    @Builder
    public FemaleCloth(Integer female_jacket, Integer female_shirts, Integer female_pants, Integer female_vest, Integer female_pullover, Integer female_cardigan,
                     Integer female_tie){

        this.female_jacket = female_jacket;
        this.female_shirts = female_shirts;
        this.female_pants = female_pants;
        this.female_vest = female_vest;
        this.female_pullover = female_pullover;
        this.female_cardigan = female_cardigan;
        this.female_tie = female_tie;
    }
}

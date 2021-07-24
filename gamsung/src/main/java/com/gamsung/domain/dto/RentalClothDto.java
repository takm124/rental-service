package com.gamsung.domain.dto;

import com.gamsung.domain.FemaleCloth;
import com.gamsung.domain.MaleCloth;
import lombok.Data;

@Data
public class RentalClothDto {

    private Integer male_jacket;
    private Integer male_shirts;
    private Integer male_pants;
    private Integer male_vest;
    private Integer male_pullover;
    private Integer male_cardigan;
    private Integer male_tie;
    private Integer belt;

    private Integer female_jacket;
    private Integer female_shirts;
    private Integer female_pants;
    private Integer female_vest;
    private Integer female_pullover;
    private Integer female_cardigan;
    private Integer female_tie;

    public MaleCloth getMaleCloth(){
        return MaleCloth.builder()
                .male_jacket(this.male_jacket)
                .male_shirts(this.male_shirts)
                .male_pants(this.male_pants)
                .male_vest(this.male_vest)
                .male_pullover(this.male_pullover)
                .male_cardigan(this.male_cardigan)
                .male_tie(this.male_tie)
                .belt(this.belt)
                .build();
    }
    public FemaleCloth getFemaleCloth(){
        return FemaleCloth.builder()
                .female_jacket(this.female_jacket)
                .female_shirts(this.female_shirts)
                .female_pants(this.female_pants)
                .female_vest(this.female_vest)
                .female_pullover(this.female_pullover)
                .female_cardigan(this.female_cardigan)
                .female_tie(this.female_tie)
                .build();
    }
}

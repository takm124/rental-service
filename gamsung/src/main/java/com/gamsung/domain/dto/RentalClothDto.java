package com.gamsung.domain.dto;

import com.gamsung.domain.Extra;
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

    //extra
    private Integer innerPants;

    private Integer stockings_coffee;
    private Integer stockings_skin;
    private Integer stockings_black;

    private Integer socks_black;
    private Integer socks_white;

    private Integer strap_top;
    private Integer tee_shirt_L;
    private Integer tee_shirt_XL;

    private Integer stockings_180D;

    private Integer knee_socks_white;
    private Integer knee_socks_gray;
    private Integer knee_socks_black;

    private Integer over_knee_socks_white;
    private Integer over_knee_socks_black;

    private Integer backpack;

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

    public Extra getExtra(){
        return Extra.builder()
                .innerPants(this.innerPants)
                .stockings_coffee(this.stockings_coffee)
                .stockings_skin(this.stockings_skin)
                .stockings_black(this.stockings_black)
                .socks_black(this.socks_black)
                .socks_white(this.socks_white)
                .strap_top(this.strap_top)
                .tee_shirt_L(this.tee_shirt_L)
                .tee_shirt_XL(this.tee_shirt_XL)
                .stockings_180D(this.stockings_180D)
                .knee_socks_white(this.knee_socks_white)
                .knee_socks_gray(this.knee_socks_gray)
                .knee_socks_black(this.knee_socks_black)
                .over_knee_socks_white(this.over_knee_socks_white)
                .over_knee_socks_black(this.over_knee_socks_black)
                .backpack(this.backpack)
                .build();
    }
}

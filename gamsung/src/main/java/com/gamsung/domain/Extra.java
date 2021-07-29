package com.gamsung.domain;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Extra {

    @Id @GeneratedValue
    @Column(name = "extra_id")
    private Long id;

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

    @Builder
    public Extra(Integer innerPants, Integer stockings_coffee, Integer stockings_skin, Integer stockings_black, Integer socks_black, Integer socks_white, Integer strap_top, Integer tee_shirt_L, Integer tee_shirt_XL, Integer stockings_180D, Integer knee_socks_white, Integer knee_socks_gray, Integer knee_socks_black, Integer over_knee_socks_white, Integer over_knee_socks_black, Integer backpack) {
        this.innerPants = innerPants;
        this.stockings_coffee = stockings_coffee;
        this.stockings_skin = stockings_skin;
        this.stockings_black = stockings_black;
        this.socks_black = socks_black;
        this.socks_white = socks_white;
        this.strap_top = strap_top;
        this.tee_shirt_L = tee_shirt_L;
        this.tee_shirt_XL = tee_shirt_XL;
        this.stockings_180D = stockings_180D;
        this.knee_socks_white = knee_socks_white;
        this.knee_socks_gray = knee_socks_gray;
        this.knee_socks_black = knee_socks_black;
        this.over_knee_socks_white = over_knee_socks_white;
        this.over_knee_socks_black = over_knee_socks_black;
        this.backpack = backpack;
    }
}

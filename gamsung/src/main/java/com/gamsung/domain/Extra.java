package com.gamsung.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Extra {

    @Id @GeneratedValue
    @Column(name = "extra_id")
    private Long id;

    private Integer innerPants;
    private Integer stockings_coffee;
    private Integer stockings_skin;
    private Integer stockings_black;

}

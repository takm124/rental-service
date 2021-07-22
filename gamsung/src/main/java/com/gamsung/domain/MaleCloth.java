package com.gamsung.domain;

import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MaleCloth {

    @Id @GeneratedValue
    @Column(name = "malecloth_id")
    private Long id;

    private Integer jacket;
    private Integer shirts;
    private Integer pants;
    private Integer vest;
    private Integer pullover;
    private Integer cardigan;
    private Integer tie;
    private Integer belt;


}

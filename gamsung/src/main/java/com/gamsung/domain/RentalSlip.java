package com.gamsung.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RentalSlip {

    @Id @GeneratedValue
    @Column(name = "rentalSlip_id")
    private Long id;

    private String rentalNum;

    private int deposit;

    private String receive; // 접수직원

    @OneToMany(mappedBy = "rentalSlip")
    private List<Customer> customers = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "malecloth_id")
    private MaleCloth maleCloth;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "femalecloth_id")
    private FemaleCloth femalCloth;

    private String payment; // 결제직원
    private String gamsung_pos; //감성포스 체크직원
    private String returner; // 반납확인

    private RentalStatus rentalStatus;

    public RentalSlip(String rentalNum, int deposit) {
        this.rentalNum = rentalNum;
        this.deposit = deposit;
    }
}

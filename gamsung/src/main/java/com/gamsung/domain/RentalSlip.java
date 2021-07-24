package com.gamsung.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class RentalSlip extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "rentalSlip_id")
    private Long id;

    private String rentalNum;

    private int deposit;

    private String receiver; // 접수직원

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

    @Enumerated(EnumType.STRING)
    private RentalStatus rentalStatus;

    public RentalSlip(String rentalNum, int deposit, String receiver, RentalStatus rentalStatus) {
        this.rentalNum = rentalNum;
        this.deposit = deposit;
        this.receiver = receiver;
        this.rentalStatus = rentalStatus;
    }

    public RentalSlip(String rentalNum, int deposit, String receiver, RentalStatus rentalStatus, Survey survey) {
        this.rentalNum = rentalNum;
        this.deposit = deposit;
        this.receiver = receiver;
        this.rentalStatus = rentalStatus;
        this.survey = survey;
    }
}

package com.gamsung.domain;

import com.gamsung.domain.dto.ClothDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@DynamicUpdate
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "extra_id")
    private Extra extra;

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

    public void setCloth(ClothDto clothDto){
        this.maleCloth = clothDto.getMaleCloth();
        this.femalCloth = clothDto.getFemaleCloth();
        this.rentalStatus = clothDto.getRentalStatus();
    }

    public void changeStatus(RentalStatus status) {
        this.rentalStatus = status;
    }

    public RentalSlip(String rentalNum, int deposit, String receiver, RentalStatus rentalStatus, Survey survey) {
        this.rentalNum = rentalNum;
        this.deposit = deposit;
        this.receiver = receiver;
        this.rentalStatus = rentalStatus;
        this.survey = survey;

    }

    public void addPaymentStaff(String staffName) {
        this.payment = staffName;
    }

    public void addReturnStaff(String staffName) {
        this.returner = staffName;
    }
}

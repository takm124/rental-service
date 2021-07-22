package com.gamsung.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class RentalSlip {

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


    public RentalSlip(String rentalNum, int deposit) {
        this.rentalNum = rentalNum;
        this.deposit = deposit;
    }
}

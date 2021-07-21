package com.gamsung.domain;

import javax.persistence.*;

@Entity
public class Customer {

    @Id @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    private String name;

    private String phoneNum;

    private String rentalNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rentalSlip_id")
    private RentalSlip rentalSlip;

    public Customer(String name, String phoneNum, String rentalNum) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.rentalNum = rentalNum;
    }


}

package com.gamsung.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Customer {

    @Id @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    private String customerName;

    private String phoneNum;

    private String rentalNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rentalSlip_id")
    private RentalSlip rentalSlip;

    public Customer(String customerName, String phoneNum, String rentalNum, RentalSlip rentalSlip) {
        this.customerName = customerName;
        this.phoneNum = phoneNum;
        this.rentalNum = rentalNum;
        if (rentalSlip != null) {
            changeRentalSlip(rentalSlip);
        }
    }

    public void changeRentalSlip(RentalSlip rentalSlip) {
        this.rentalSlip = rentalSlip;
        rentalSlip.getCustomers().add(this);
    }



}

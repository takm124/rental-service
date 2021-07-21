package com.gamsung.domain;

import javax.persistence.*;

@Entity
public class Staff {

    @Id @GeneratedValue
    @Column(name = "staff_id")
    private Long id;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private Place place;
}

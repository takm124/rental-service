package com.gamsung.controller;

import com.gamsung.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InitSetting {

    private final InitSettingService initSettingService;

    @PostConstruct
    public void init(){
        initSettingService.init();
    }

    @Component
    static class InitSettingService{

        @PersistenceContext private EntityManager em;

        @Transactional
        public void init(){
            Staff staffA = new Staff(UUID.randomUUID().toString(),"staffA","test1","1111", "01012341234",Place.LWJS, JobPosition.STAFF);
            Staff staffB = new Staff(UUID.randomUUID().toString(),"staffB","test2","1111", "01012341234",Place.LWJS, JobPosition.STAFF);
            Staff staffADMIN = new Staff(UUID.randomUUID().toString(),"staffADMIN","admin","1111", "01012341234",Place.LWJS, JobPosition.ADMIN);
            Staff staffADMIN2 = new Staff(UUID.randomUUID().toString(),"adminEVER","admin2","2222", "01012341234",Place.EVER, JobPosition.ADMIN);
            em.persist(staffA);
            em.persist(staffB);
            em.persist(staffADMIN);
            em.persist(staffADMIN2);

            RentalSlip rentalSlip1 = new RentalSlip("20210721001", 30000, "staffA", RentalStatus.RECEIVED);
            RentalSlip rentalSlip2 = new RentalSlip("20210721002", 20000, "staffB",RentalStatus.RECEIVED);
            em.persist(rentalSlip1);
            em.persist(rentalSlip2);

            Customer customerA = new Customer("customerA", "01011111111", "20210721001", rentalSlip1);
            Customer customerB = new Customer("customerB", "01011111111", "20210721001", rentalSlip1);
            Customer customerC = new Customer("customerC", "01011111111", "20210721001", rentalSlip1);
            Customer customerD = new Customer("customerD", "01022222222", "20210721002", rentalSlip2);
            Customer customerE = new Customer("customerE", "01022222222", "20210721002", rentalSlip2);
            em.persist(customerA);
            em.persist(customerB);
            em.persist(customerC);
            em.persist(customerD);
            em.persist(customerE);
        }

    }
}

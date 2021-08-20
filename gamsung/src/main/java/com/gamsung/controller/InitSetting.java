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
            Staff test = new Staff(UUID.randomUUID().toString(),"staffA","test1","1111", "01012341234",Place.LWJS, JobPosition.STAFF);
            Staff adminLWJS = new Staff(UUID.randomUUID().toString(),"staffADMIN","admin","1111", "01012341234",Place.LWJS, JobPosition.ADMIN);
            Staff adminEVER = new Staff(UUID.randomUUID().toString(),"adminEVER","admin2","2222", "01012341234",Place.EVER, JobPosition.ADMIN);
            em.persist(test);
            em.persist(adminLWJS);
            em.persist(adminEVER);

        }

    }
}

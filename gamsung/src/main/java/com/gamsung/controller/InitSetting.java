package com.gamsung.controller;

import com.gamsung.domain.Place;
import com.gamsung.domain.Staff;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
            Staff staffA = new Staff("staffA","test1","1111", Place.LWJS);
            Staff staffB = new Staff("staffB","test2","1111", Place.LWJS);
            em.persist(staffA);
            em.persist(staffB);
        }

    }
}

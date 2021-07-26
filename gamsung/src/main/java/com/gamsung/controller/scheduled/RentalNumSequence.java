package com.gamsung.controller.scheduled;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class RentalNumSequence {

    private static int rentalNumSequence = 1;

    public int getSequence(){
        return rentalNumSequence++;
    }

    @Scheduled(cron = "0 0 0 * * *") // 매일자정 초기화
    public void initRentalSequence(){
        rentalNumSequence = 1;
    }
}

package com.gamsung.controller;

import com.gamsung.domain.RentalStatus;
import com.gamsung.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RentalService rentalService;

    @GetMapping("/main")
    public String main(Model model){
        long todayCustomer = rentalService.countTodayCustomer();
        long todayRentalSlip = rentalService.countTodayRentalSlip();
        long statusReceived = rentalService.countRentalSlip(RentalStatus.RECEIVED);
        long statusPayed = rentalService.countRentalSlip(RentalStatus.PAYED);

        model.addAttribute("todayCustomer", todayCustomer);
        model.addAttribute("todayRentalSlip", todayRentalSlip);
        model.addAttribute("statusReceived", statusReceived);
        model.addAttribute("statusPayed", statusPayed);

        return "common/mainPage";
    }

}

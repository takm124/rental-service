package com.gamsung.controller;

import com.gamsung.controller.scheduled.RentalNumSequence;
import com.gamsung.domain.Customer;
import com.gamsung.domain.RentalSlip;
import com.gamsung.domain.Survey;
import com.gamsung.domain.dto.SurveyDto;
import com.gamsung.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @Autowired
    RentalNumSequence rentalNumSequence;

    private int rentalSequence;

    @GetMapping("/receive")
    public String receive(){
//        model.addAttribute("survey", new SurveyDto());
        return "rental/receiveForm";
    }

    @PostMapping("/receive")
    public String customer(@RequestParam(value="name", required=true) List<String> names,
                           @RequestParam(value="phoneNum", required = true) List<String> phoneNums,
                           @RequestParam int deposit){

        // 렌탈번호 생성 및 DB에 같이 넣기
        String rentalNum = makeRentalNum();

        Customer customer1 = new Customer(names.get(0), phoneNums.get(0), rentalNum);
        Customer customer2 = new Customer(names.get(1), phoneNums.get(1), rentalNum);

        rentalService.saveCustomer(customer1);
        rentalService.saveCustomer(customer2);
        RentalSlip rentalSlip = new RentalSlip(rentalNum,deposit);
        rentalService.saveRentalSlip(rentalSlip);

//        Survey survey = new Survey(surveyDto);
//        rentalService.saveSurvey(survey);


        return "rental/receiveForm";
    }

    public String makeRentalNum(){
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        int seq = rentalNumSequence.getSequence();
        String seqFormat = String.format("%03d", seq);
        return now+seqFormat;
    }

    @GetMapping("/rentalSlip")
    public String rentalSlipList(){

        return "rental/rentalSlipList";
    }

    @GetMapping("/reatalSlip/{rentalNum}")
    public String rentalSlipForm(){
        return "rental/rentalSlipForm";
    }



}

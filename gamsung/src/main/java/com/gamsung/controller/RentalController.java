package com.gamsung.controller;

import com.gamsung.SessionConst;
import com.gamsung.controller.scheduled.RentalNumSequence;
import com.gamsung.domain.*;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        return "rental/receiveForm";
    }

    @PostMapping("/receive")
    public String customer(@RequestParam(value="name", required=true) List<String> names,
                           @RequestParam(value="phoneNum", required = true) List<String> phoneNums,
                           @RequestParam int deposit,
                           SurveyDto surveyDto,
                           HttpServletRequest request){
        // 현재 로그인되어있는 스탭
        HttpSession session = request.getSession();
        Staff loginStaff = (Staff) session.getAttribute(SessionConst.LOGIN_STAFF);

        // 설문
        Survey survey = new Survey(surveyDto);
        rentalService.saveSurvey(survey);

        String rentalNum = makeRentalNum(); // 렌탈번호 생성 및 DB에 같이 넣기
        RentalSlip rentalSlip = new RentalSlip(rentalNum,deposit, loginStaff.getStaffName(), RentalStatus.RECEIVED, survey);
        RentalSlip saveRentalSlip = rentalService.saveRentalSlip(rentalSlip); // 재희) id추출해서 redirect할때 쓸꺼에

        Customer customer1 = new Customer(names.get(0), phoneNums.get(0), rentalNum, rentalSlip);
        Customer customer2 = new Customer(names.get(1), phoneNums.get(1), rentalNum, rentalSlip);
        rentalService.saveCustomer(customer1);
        rentalService.saveCustomer(customer2);





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

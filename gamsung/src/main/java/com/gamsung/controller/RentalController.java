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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                           HttpServletRequest request,
                           RedirectAttributes redirectAttributes){
        // 현재 로그인되어있는 스탭
        HttpSession session = request.getSession();
        Staff loginStaff = (Staff) session.getAttribute(SessionConst.LOGIN_STAFF);

        // 설문
        Survey survey = new Survey(surveyDto);
        rentalService.saveSurvey(survey);

        String rentalNum = makeRentalNum(); // 렌탈번호 생성 및 DB에 같이 넣기
        RentalSlip rentalSlip = new RentalSlip(rentalNum,deposit, loginStaff.getStaffName(), RentalStatus.RECEIVED, survey);
        rentalService.saveRentalSlip(rentalSlip);

        for(int i = 0 ; i < names.size() ; i++){
            if(names.get(i).length() != 0 && phoneNums.get(i).length() != 0){
                rentalService.saveCustomer(new Customer(names.get(i), phoneNums.get(i), rentalNum, rentalSlip));
            }
        }

        redirectAttributes.addAttribute("rentalNum", rentalNum);

        return "redirect:/receiveCheck";
    }

    @GetMapping("/receiveCheck")
    public String receiveCheck(String rentalNum, Model model){
        model.addAttribute("rentalNum", rentalNum);
        return "rental/receiveCheck";
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

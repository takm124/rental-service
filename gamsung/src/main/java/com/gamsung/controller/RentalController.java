package com.gamsung.controller;

import com.gamsung.SessionConst;
import com.gamsung.controller.scheduled.RentalNumSequence;
import com.gamsung.domain.*;
import com.gamsung.domain.dto.ClothDto;
import com.gamsung.domain.dto.RentalClothDto;
import com.gamsung.domain.dto.RentalSlipListDto;
import com.gamsung.domain.dto.SurveyDto;
import com.gamsung.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

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
    public String rentalSlipList(Model model){
        List<RentalSlipListDto> rentalSlips = rentalService.rentalSlipList();
        model.addAttribute("rentalSlips", rentalSlips);
        return "rental/rentalSlipList";
    }

    //rentalSlip 이동
    @GetMapping("/rentalSlip/{rentalNum}")
    public String rentalSlipForm(@PathVariable("rentalNum") String rentalNum, Model model){
        RentalSlip rentalSlip = rentalService.findRentalSlip(rentalNum);
        List<Customer> customers = rentalService.getCustomer(rentalNum);

        model.addAttribute("customers", customers);
        model.addAttribute("rentalSlip", rentalSlip);
        return "rental/rentalSlipForm";
    }

    //결제완료
    @PostMapping("/rentalSlip/{rentalNum}")
    public String payment(@PathVariable("rentalNum") String rentalNum){


        RentalStatus rentalStatus = RentalStatus.PAYED;
        RentalSlip rentalSlip = rentalService.findRentalSlip(rentalNum);
        rentalSlip.changeStatus(rentalStatus);

        return "redirect:/rentalSlip";
    }

    //수정 폼 이동
    @GetMapping("/rentalSlip/{rentalNum}/edit")
    public String rentalSlipEditForm(@PathVariable("rentalNum") String rentalNum, Model model){
        RentalSlip rentalSlip = rentalService.findRentalSlip(rentalNum);
        List<Customer> customers = rentalService.getCustomer(rentalNum);

        model.addAttribute("customers", customers);
        model.addAttribute("rentalSlip", rentalSlip);
        return "rental/rentalSlipEditForm";
    }

    //수정완료
    @PostMapping("/rentalSlip/{rentalNum}/edit")
    public String rentalSlipEdit(@PathVariable("rentalNum") String rentalNum, RentalClothDto rentalClothDto){
        log.info("rentalNum = {}", rentalNum);
        log.info("RentalClothDto = {}", rentalClothDto.toString());

        MaleCloth maleCloth = rentalClothDto.getMaleCloth();
        FemaleCloth femaleCloth = rentalClothDto.getFemaleCloth();

        RentalSlip rentalSlip = rentalService.findRentalSlip(rentalNum);
        ClothDto cloth = new ClothDto(maleCloth, femaleCloth);

        rentalSlip.setCloth(cloth);

        RentalSlip result = rentalService.saveRentalSlip(rentalSlip);

        return "redirect:/rentalSlip/{rentalNum}";
    }



}

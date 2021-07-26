package com.gamsung.controller;

import com.gamsung.SessionConst;
import com.gamsung.controller.scheduled.RentalNumSequence;
import com.gamsung.domain.*;
import com.gamsung.domain.dto.*;
import com.gamsung.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    private final RentalNumSequence rentalNumSequence;

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

        return "redirect:/receiveCheck/{rentalNum}";
    }

    @GetMapping("/receiveCheck/{rentalNum}")
    public String receiveCheck(@PathVariable String rentalNum, Model model){
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
    public String rentalSlipList(Model model){
        List<RentalSlipListDto> rentalSlips = rentalService.rentalSlipList();
        model.addAttribute("rentalSlips", rentalSlips);
        return "rental/rentalSlipList";
    }

    //rentalSlip 이동
    @GetMapping("/rentalSlip/{rentalNum}")
    public String rentalSlipForm(@PathVariable("rentalNum") String rentalNum, Model model){
        RentalSlip rentalSlip = rentalService.findRentalSlip(rentalNum);
        List<CustomerDto> customers = rentalService.getCustomer(rentalNum);

        model.addAttribute("customers", customers);
        model.addAttribute("rentalSlip", rentalSlip);
        return "rental/rentalSlipForm";
    }

    //결제완료
    @PostMapping("/rentalSlip/{rentalNum}")
    public String payment(@PathVariable("rentalNum") String rentalNum, HttpServletRequest request){
        // 현재 로그인되어있는 스탭
        HttpSession session = request.getSession();
        Staff loginStaff = (Staff) session.getAttribute(SessionConst.LOGIN_STAFF);

        RentalSlip rentalSlip = rentalService.findRentalSlip(rentalNum);
        rentalService.updatePayment(rentalSlip.getId(), RentalStatus.PAYED, loginStaff.getStaffName());

        return "redirect:/rentalSlip";
    }

    //수정 폼 이동
    @GetMapping("/rentalSlip/{rentalNum}/edit")
    public String rentalSlipEditForm(@PathVariable("rentalNum") String rentalNum, Model model){
        RentalSlip rentalSlip = rentalService.findRentalSlip(rentalNum);
        List<CustomerDto> customers = rentalService.getCustomer(rentalNum);

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

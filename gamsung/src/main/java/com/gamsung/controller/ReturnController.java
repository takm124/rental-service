package com.gamsung.controller;

import com.gamsung.SessionConst;
import com.gamsung.domain.Customer;
import com.gamsung.domain.RentalSlip;
import com.gamsung.domain.RentalStatus;
import com.gamsung.domain.Staff;
import com.gamsung.domain.dto.RentalSlipListDto;
import com.gamsung.domain.dto.ReturnSlipListDto;
import com.gamsung.service.RentalService;
import com.gamsung.service.ReturnService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReturnController {

    private final ReturnService returnService;
    private final RentalService rentalService;

    //반납 리스트
    @GetMapping("/returnSlip")
    public String returnSlipList(Model model){
        List<ReturnSlipListDto> returnSlips = returnService.returnSlipList(); //PAYED 상태 rentalSlip 조회
        model.addAttribute("returnSlips", returnSlips);
        return "return/returnSlipList";
    }

    //반납 전표
    @GetMapping("/returnSlip/{rentalNum}")
    public String rentalSlipForm(@PathVariable("rentalNum") String rentalNum, Model model){
        RentalSlip rentalSlip = rentalService.findRentalSlip(rentalNum);
        List<Customer> customers = rentalService.getCustomer(rentalNum);

        model.addAttribute("customers", customers);
        model.addAttribute("returnSlip", rentalSlip);
        return "return/returnSlipForm";
    }


    //반납 완료
    @PostMapping("/returnSlip/{rentalNum}")
    public String returned(@PathVariable("rentalNum") String rentalNum, HttpServletRequest request){

        // 현재 로그인되어있는 스탭
        HttpSession session = request.getSession();
        Staff loginStaff = (Staff) session.getAttribute(SessionConst.LOGIN_STAFF);

        RentalSlip rentalSlip = rentalService.findRentalSlip(rentalNum);
        rentalService.updateReturned(rentalSlip.getId(), RentalStatus.RETURNED, loginStaff.getStaffName());

        return "redirect:/returnSlip";
    }
}

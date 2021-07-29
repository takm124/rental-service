package com.gamsung.controller;

import com.gamsung.SessionConst;
import com.gamsung.domain.*;
import com.gamsung.domain.dto.AdminRentalSlipListDto;
import com.gamsung.domain.dto.CustomerDto;
import com.gamsung.domain.dto.NewStaffDto;
import com.gamsung.domain.dto.StaffDto;
import com.gamsung.service.AdminService;
import com.gamsung.service.RentalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final RentalService rentalService;

    // 현재 로그인되어있는 관리자의 근무위치를 받아온다.
    private Place getStaffPlace(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Staff loginStaff = (Staff) session.getAttribute(SessionConst.LOGIN_STAFF);
        Place place = loginStaff.getPlace();
        return place;
    }

    // 스탭 계정 전체 조회
    @GetMapping("/staffAccounts")
    public String staffAccounts(Model model, HttpServletRequest request) {
        Place place = getStaffPlace(request);
        List<StaffDto> staffAccounts = adminService.searchStaffList(place);

        model.addAttribute("staffAccounts", staffAccounts);

        return "admin/staffAccounts";
    }

    // 스탭 계정 생성
    @GetMapping("/staffAccounts/new")
    public String newStaff(@ModelAttribute("newStaffDto") NewStaffDto newStaffDto) {
        return "admin/newStaffForm";
    }

    // 스탭 계정 생성
    @PostMapping("/staffAccounts/new")
    public String makeNewStaff(@ModelAttribute NewStaffDto newStaffDto, HttpServletRequest request) {
        Place place = getStaffPlace(request);
        Staff staff = new Staff(
                UUID.randomUUID().toString(),
                newStaffDto.getStaffName(),
                newStaffDto.getLoginId(),
                newStaffDto.getPassword(),
                newStaffDto.getPhoneNum(),
                place, JobPosition.STAFF);

        adminService.saveStaff(staff);

        return "redirect:/admin/staffAccounts";
    }

    // 스탭 계정 수정
    @GetMapping("/staffAccounts/edit/{staffNum}")
    public String edit(@PathVariable String staffNum, Model model) {
        Staff findStaff = adminService.findStaff(staffNum);
        NewStaffDto staffDto = new NewStaffDto(findStaff.getStaffNum(), findStaff.getStaffName(), findStaff.getPhoneNumber(), findStaff.getLoginId(), findStaff.getPassword());
        model.addAttribute("staffDto", staffDto);
        return "admin/editStaffForm";
    }

    // 스탭 계정 수정
    @PostMapping("/staffAccounts/edit/{staffNum}")
    public String editStaff(@PathVariable String staffNum, @ModelAttribute NewStaffDto staffDto) {
        log.info("newStaffDto = {}", staffDto);
        Staff findStaff = adminService.findStaff(staffNum);

        findStaff.editStaff(staffDto);
        adminService.saveStaff(findStaff);
        return "redirect:/admin/staffAccounts";
    }

    // 스탭계정 삭제
    @GetMapping("/staffAccounts/delete/{staffNum}")
    public String deleteStaff(@PathVariable String staffNum) {
        Staff deleteStaff = adminService.findStaff(staffNum);
        adminService.deleteStaff(deleteStaff);
        return "redirect:/admin/staffAccounts";
    }

    // 스탭 이름으로 검색
    @GetMapping("/staffAccount")
    public String getStaffAccountByStaffName(@RequestParam("staffName") String staffName, Model model) {
        Staff findStaff = adminService.findStaffByStaffName(staffName);
        return "redirect:/admin/staffAccounts/edit/" + findStaff.getStaffNum();
    }


    // 전표 전체 조회
    @GetMapping("/rentalSlipList")
    public String rentalSlipList(Model model) {
        List<AdminRentalSlipListDto> rentalSlips = adminService.rentalSlipList();
        model.addAttribute("rentalSlips", rentalSlips);
        return "admin/rentalSlipList";
    }

    // 특정 전표 상세보기
    @GetMapping("/rentalSlipDetail/{rentalNum}")
    public String rentalSlipDetail(@PathVariable String rentalNum, Model model) {
        AdminRentalSlipListDto rentalSlipDetail = adminService.rentalSlipDetail(rentalNum);
        List<CustomerDto> customers = rentalService.getCustomer(rentalNum);
        RentalSlip rentalSlip = rentalService.findRentalSlip(rentalNum);


        model.addAttribute("rentalSlipDetail", rentalSlipDetail);
        model.addAttribute("customers", customers);
        model.addAttribute("rentalSlip", rentalSlip);
        return "admin/rentalSlipDetail";
    }

    // 특정전표 조회 - 전표번호
    @GetMapping("/rentalSlip/search")
    public String rentalSlipSearchByRentalNum(@RequestParam("searchText") String searchText, @RequestParam("searchType") String searchType, Model model){

        if(searchType.equals("customerName")){
            List<AdminRentalSlipListDto> findRentalSlips = adminService.adminRentalSlipByCustomerName(searchText);
            model.addAttribute("rentalSlips", findRentalSlips);
            model.addAttribute("searchText", searchText);
            return "admin/rentalSlipSearchedList";
        }
        if(searchType.equals("enterDate")){
            List<AdminRentalSlipListDto> findRentalSlips = adminService.adminRentalSlipByEnterDate(searchText);
            model.addAttribute("rentalSlips", findRentalSlips);
            model.addAttribute("searchText", searchText);
            return "admin/rentalSlipSearchedList";
        }
        // 전표번호 조회인 경우
        AdminRentalSlipListDto rentalSlipDetail = adminService.rentalSlipDetail(searchText);
        if (rentalSlipDetail == null) {
            // validation 추가해서 에러메세지 띄워주기 - 해당 번호로 검색불가라고
            return "redirect:/admin/rentalSlipList";
        }
        return "redirect:/admin/rentalSlipDetail/"+rentalSlipDetail.getRentalNum();
    }


}

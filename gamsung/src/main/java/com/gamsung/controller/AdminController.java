package com.gamsung.controller;

import com.gamsung.SessionConst;
import com.gamsung.domain.Customer;
import com.gamsung.domain.JobPosition;
import com.gamsung.domain.Place;
import com.gamsung.domain.Staff;
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

    public Map<String, String> places() {
        Map<String, String> places = new HashMap<>();
        places.put("LWJS", "잠실롯데월드점");
        places.put("LWBS", "부산롯데월드점");
        places.put("LWSC", "본점");
        places.put("EVER", "에버랜드점");

        return places;
    }

    @GetMapping
    public String adminPage(Model model, HttpServletRequest request) {
        Place staffPlace = getStaffPlace(request);

        Map<String, String> places = places();
        String staffPlaceName = places.get(staffPlace.toString());
        model.addAttribute("place", staffPlaceName);

        return "admin/admin_main";
    }

    private Place getStaffPlace(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Staff loginStaff = (Staff) session.getAttribute(SessionConst.LOGIN_STAFF);
        Place place = loginStaff.getPlace();
        return place;
    }

    @GetMapping("/staffAccounts")
    public String staffAccounts(Model model, HttpServletRequest request) {
        Place place = getStaffPlace(request);
        List<StaffDto> staffAccounts = adminService.searchStaffList(place);

        model.addAttribute("staffAccounts", staffAccounts);

        return "admin/staffAccounts";
    }

    @GetMapping("/staffAccounts/new")
    public String newStaff(@ModelAttribute("newStaffDto") NewStaffDto newStaffDto) {
        return "admin/newStaffForm";
    }

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

    @GetMapping("/staffAccounts/edit/{staffNum}")
    public String edit(@PathVariable String staffNum, Model model) {
        Staff findStaff = adminService.findStaff(staffNum);
        NewStaffDto staffDto = new NewStaffDto(findStaff.getStaffNum(), findStaff.getStaffName(), findStaff.getPhoneNumber(), findStaff.getLoginId(), findStaff.getPassword());
        model.addAttribute("staffDto", staffDto);
        return "admin/editStaffForm";
    }

    @PostMapping("/staffAccounts/edit/{staffNum}")
    public String editStaff(@PathVariable String staffNum, @ModelAttribute NewStaffDto staffDto) {
        log.info("newStaffDto = {}", staffDto);
        Staff findStaff = adminService.findStaff(staffNum);

        findStaff.editStaff(staffDto);
        adminService.saveStaff(findStaff);
        return "redirect:/admin/staffAccounts";
    }

    @GetMapping("/staffAccounts/delete/{staffNum}")
    public String deleteStaff(@PathVariable String staffNum) {
        Staff deleteStaff = adminService.findStaff(staffNum);
        adminService.deleteStaff(deleteStaff);
        return "redirect:/admin/staffAccounts";
    }


    @GetMapping("/rentalSlipList")
    public String rentalSlipList(Model model) {
        List<AdminRentalSlipListDto> rentalSlips = adminService.rentalSlipList();
        model.addAttribute("rentalSlips", rentalSlips);
        return "admin/rentalSlipList";
    }

    @GetMapping("/rentalSlipDetail/{rentalNum}")
    public String rentalSlipDetail(@PathVariable String rentalNum, Model model) {
        AdminRentalSlipListDto rentalSlipDetail = adminService.rentalSlipDetail(rentalNum);
        List<CustomerDto> customers = rentalService.getCustomer(rentalNum);



        model.addAttribute("rentalSlipDetail", rentalSlipDetail);
        model.addAttribute("customers", customers);
        return "admin/rentalSlipDetail";
    }
}

package com.gamsung.controller;

import com.gamsung.SessionConst;
import com.gamsung.domain.Place;
import com.gamsung.domain.Staff;
import com.gamsung.domain.dto.NewStaffDto;
import com.gamsung.domain.dto.StaffDto;
import com.gamsung.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    public Map<String, String> places() {
        Map<String, String> places = new HashMap<>();
        places.put("LWJS", "잠실롯데월드점");
        places.put("LWBS", "부산롯데월드점");
        places.put("LWSC", "본점");
        places.put("EVER", "에버랜드점");

        return places;
    }

    @GetMapping("/manage")
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

    @GetMapping("/adminStaffAccounts")
    public String staffAccounts(Model model, HttpServletRequest request) {
        Place place = getStaffPlace(request);
        List<StaffDto> staffAccounts = adminService.searchStaffList(place);

        model.addAttribute("staffAccounts", staffAccounts);

        return "admin/staffAccounts";
    }

    @GetMapping("/newStaff")
    public String newStaff(@ModelAttribute("newStaffDto") NewStaffDto newStaffDto) {
        return "admin/newStaffForm";
    }

    @PostMapping("/newStaff")
    public String makeNewStaff(@ModelAttribute NewStaffDto newStaffDto) {

        return "";
    }
}

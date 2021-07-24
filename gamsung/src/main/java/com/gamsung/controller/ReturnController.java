package com.gamsung.controller;

import com.gamsung.domain.dto.RentalSlipListDto;
import com.gamsung.domain.dto.ReturnSlipListDto;
import com.gamsung.service.RentalService;
import com.gamsung.service.ReturnService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ReturnController {

    private final ReturnService returnService;

    @GetMapping("/returnSlip")
    public String returnSlipList(Model model){
        List<ReturnSlipListDto> returnSlips = returnService.returnSlipList();
        model.addAttribute("returnSlips", returnSlips);
        return "return/returnSlipList";
    }
}

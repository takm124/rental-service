package com.gamsung.controller;

import com.gamsung.SessionConst;
import com.gamsung.domain.Staff;
import com.gamsung.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping(value = {"/login", "/"})
    public String login(@ModelAttribute("loginForm") LoginForm loginForm){
        return "login/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "login/login";
        }

        Staff loginStaff = loginService.login(form.getLoginId(), form.getPassword());
        if (loginStaff == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/login";
        }

        // 로그인 성공 처리
        HttpSession session = request.getSession(); // 세션이 있으면 있는 세션 반환, 없으면 신규 세션을 생성
        session.setAttribute(SessionConst.LOGIN_STAFF, loginStaff);

        String workPlaceName = places().get(loginStaff.getPlace().toString());
        session.setAttribute("workPlaceName", workPlaceName);
        session.setAttribute("position", loginStaff.getJobPosition());
        return "redirect:/main";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // 기존세션이 있을경우에 반환ㄴ, 없으면 null
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    public Map<String, String> places() {
        Map<String, String> places = new HashMap<>();
        places.put("LWJS", "잠실롯데월드점");
        places.put("LWBS", "부산롯데월드점");
        places.put("LWSC", "본점");
        places.put("EVER", "에버랜드점");

        return places;
    }
}

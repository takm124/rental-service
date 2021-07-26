package com.gamsung.interceptor;

import com.gamsung.SessionConst;
import com.gamsung.domain.JobPosition;
import com.gamsung.domain.Staff;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AdminCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("관리자 페이지 관련 인터셉터 실행 : {}", requestURI);

        HttpSession session = request.getSession();
        Staff loginStaff = (Staff) session.getAttribute(SessionConst.LOGIN_STAFF);
        JobPosition jobPosition = loginStaff.getJobPosition();
        if (jobPosition != JobPosition.ADMIN) {
            log.info("접근 권한 없음");
            response.sendRedirect("/main");
            return false;
        }

        return true;
    }
}

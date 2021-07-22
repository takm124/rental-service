package com.gamsung.service;

import com.gamsung.domain.Staff;
import com.gamsung.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final StaffRepository staffRepository;

    /**
     * @return null 로그인 실패
     */
    public Staff login(String loginId, String password) {
        Staff findStaff = staffRepository.findByLoginId(loginId);
        if (findStaff == null) {
            return null;
        }

        if (findStaff.getPassword().equals(password)) {
            return findStaff;
        } else {
            return null;
        }
    }
}

package com.gamsung.repository;

import com.gamsung.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long>, StaffRepositoryCustom {
    Staff findByLoginId(String loginId);
    Staff findByStaffName(String staffName);
    Staff findByStaffNum(String staffNum);
}

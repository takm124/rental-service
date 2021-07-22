package com.gamsung.repository;

import com.gamsung.domain.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    Staff findByLoginId(String loginId);
}

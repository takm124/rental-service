package com.gamsung.repository;

import com.gamsung.domain.Place;
import com.gamsung.domain.dto.StaffDto;

import java.util.List;

public interface StaffRepositoryCustom {
    List<StaffDto> searchStaffList(Place place);
}

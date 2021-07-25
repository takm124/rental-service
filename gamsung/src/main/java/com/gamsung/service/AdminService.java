package com.gamsung.service;

import com.gamsung.domain.Place;
import com.gamsung.domain.dto.StaffDto;
import com.gamsung.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final StaffRepository staffRepository;

    public List<StaffDto> searchStaffList(Place place) {
        return staffRepository.searchStaffList(place);
    }
}

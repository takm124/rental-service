package com.gamsung.service;

import com.gamsung.domain.Place;
import com.gamsung.domain.Staff;
import com.gamsung.domain.dto.AdminRentalSlipListDto;
import com.gamsung.domain.dto.StaffDto;
import com.gamsung.repository.RentalRepository;
import com.gamsung.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final StaffRepository staffRepository;
    private final RentalRepository rentalRepository;

    public List<StaffDto> searchStaffList(Place place) {
        return staffRepository.searchStaffList(place);
    }

    public Staff saveStaff(Staff staff){
        return staffRepository.save(staff);
    }

    public Staff findStaff(String staffNum) {
        return staffRepository.findByStaffNum(staffNum);
    }

    public void deleteStaff(Staff staff) {
        staffRepository.delete(staff);
    }

    public List<AdminRentalSlipListDto> rentalSlipList(){
        return rentalRepository.adminRentalSlipList();
    }

    public AdminRentalSlipListDto rentalSlipDetail(String rentalNum){
        return rentalRepository.adminRentalSlipDetail(rentalNum);
    }
}

package com.gamsung.repository;

import com.gamsung.domain.RentalStatus;
import com.gamsung.domain.dto.AdminRentalSlipListDto;
import com.gamsung.domain.dto.RentalSearchCondition;
import com.gamsung.domain.dto.RentalSlipListDto;
import com.gamsung.domain.dto.ReturnSlipListDto;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


public interface RentalRepositoryCustom {

    List<RentalSlipListDto> searchRentalSlipList();
    List<RentalSlipListDto> keywordRentalSlipList(String customerName);
    List<ReturnSlipListDto> searchReturnSlipList();


    List<AdminRentalSlipListDto> adminRentalSlipList();
    AdminRentalSlipListDto adminRentalSlipDetail(String rentalNum);
    List<AdminRentalSlipListDto> adminRentalSlipByCustomerName(String customerName);
    List<AdminRentalSlipListDto> adminRentalSlipByEnterDate(String enterDate);

    long countRentalSlip(RentalStatus rentalStatus);

    long countTodayRentalSlip(String today);
}

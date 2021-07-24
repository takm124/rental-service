package com.gamsung.repository;

import com.gamsung.domain.dto.RentalSearchCondition;
import com.gamsung.domain.dto.RentalSlipListDto;
import com.gamsung.domain.dto.ReturnSlipListDto;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface RentalRepositoryCustom {

    List<RentalSlipListDto> searchRentalSlipList();
    List<ReturnSlipListDto> searchReturnSlipList();
}

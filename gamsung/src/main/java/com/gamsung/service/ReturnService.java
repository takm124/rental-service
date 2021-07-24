package com.gamsung.service;

import com.gamsung.domain.dto.RentalSlipListDto;
import com.gamsung.domain.dto.ReturnSlipListDto;
import com.gamsung.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReturnService {

    private final RentalRepository rentalRepository;

    public List<ReturnSlipListDto> returnSlipList() {
        return rentalRepository.searchReturnSlipList();
    }

}

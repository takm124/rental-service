package com.gamsung.service;

import com.gamsung.domain.*;
import com.gamsung.domain.dto.RentalSlipListDto;
import com.gamsung.repository.CustomerRepository;
import com.gamsung.repository.RentalRepository;
import com.gamsung.repository.RentalRepositoryImpl;
import com.gamsung.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final CustomerRepository customerRepository;
    private final RentalRepository rentalRepository;
    private final SurveyRepository surveyRepository;

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public RentalSlip saveRentalSlip(RentalSlip rentalSlip) {
        return rentalRepository.save(rentalSlip);
    }

    public RentalSlip findRentalSlip(String rentalNum){ return rentalRepository.findByRentalNum(rentalNum);}

    public Survey saveSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    public List<RentalSlipListDto> rentalSlipList() {
        return rentalRepository.searchRentalSlipList();
    }

    public List<Customer> getCustomer(String rentalNum) {return customerRepository.findByRentalNum(rentalNum);}

    @Transactional
    public void updateStatus(Long id, RentalStatus rentalStatus){
        Optional<RentalSlip> rentalSlip = rentalRepository.findById(id);
        rentalSlip.get().changeStatus(rentalStatus);

    }



}

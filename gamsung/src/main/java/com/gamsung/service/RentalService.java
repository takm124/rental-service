package com.gamsung.service;

import com.gamsung.domain.*;
import com.gamsung.domain.dto.CustomerDto;
import com.gamsung.domain.dto.RentalSlipListDto;
import com.gamsung.repository.CustomerRepository;
import com.gamsung.repository.RentalRepository;
import com.gamsung.repository.RentalRepositoryImpl;
import com.gamsung.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<CustomerDto> getCustomer(String rentalNum) {
        List<Customer> customers = customerRepository.findByRentalNum(rentalNum);
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            customerDtos.add(new CustomerDto(customer.getCustomerName(), customer.getPhoneNum(), customer.getRentalNum()));
        }
        return customerDtos;
    }




}

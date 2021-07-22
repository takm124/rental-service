package com.gamsung.service;

import com.gamsung.domain.Customer;
import com.gamsung.domain.RentalSlip;
import com.gamsung.domain.Survey;
import com.gamsung.repository.CustomerRepository;
import com.gamsung.repository.RentalRepository;
import com.gamsung.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Survey saveSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

}

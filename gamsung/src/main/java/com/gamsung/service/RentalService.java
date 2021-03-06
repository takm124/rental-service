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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public List<CustomerDto> getCustomer(String rentalNum) {
        List<Customer> customers = customerRepository.findByRentalNum(rentalNum);
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            customerDtos.add(new CustomerDto(customer.getCustomerName(), customer.getPhoneNum(), customer.getRentalNum()));
        }
        return customerDtos;
    }

    public List<RentalSlipListDto> keywordSearch(String customerName){
        return rentalRepository.keywordRentalSlipList(customerName);
    }


    @Transactional
    public void rentalSlipDelete(String rentalNum){
        rentalRepository.deleteByRentalNum(rentalNum);
    }

    public long countRentalSlip(RentalStatus rentalStatus) {
        return rentalRepository.countRentalSlip(rentalStatus);
    }

    public long countTodayRentalSlip(){
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return rentalRepository.countTodayRentalSlip(today);
    }

    public long countTodayCustomer(){
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return customerRepository.countTodayCustomer(today);
    }


    @Transactional
    public void updatePayment(Long id, RentalStatus rentalStatus, String staffName){
        Optional<RentalSlip> rentalSlip = rentalRepository.findById(id);
        rentalSlip.get().changeStatus(rentalStatus);
        rentalSlip.get().addPaymentStaff(staffName);
    }

    @Transactional
    public void updateReturned(Long id, RentalStatus rentalStatus, String staffName){
        Optional<RentalSlip> rentalSlip = rentalRepository.findById(id);
        rentalSlip.get().changeStatus(rentalStatus);
        rentalSlip.get().addReturnStaff(staffName);
    }



}

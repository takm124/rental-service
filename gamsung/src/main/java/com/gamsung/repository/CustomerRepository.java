package com.gamsung.repository;

import com.gamsung.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByRentalNum(String rentalNum);
}

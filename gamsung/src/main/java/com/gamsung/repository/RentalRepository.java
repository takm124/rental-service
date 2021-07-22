package com.gamsung.repository;

import com.gamsung.domain.RentalSlip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<RentalSlip, Long>, RentalRepositoryCustom {


}

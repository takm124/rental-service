package com.gamsung.repository;

import com.gamsung.domain.Customer;
import com.gamsung.domain.QCustomer;
import com.gamsung.domain.QRentalSlip;
import com.gamsung.domain.RentalStatus;
import com.gamsung.domain.dto.QRentalSlipListDto;
import com.gamsung.domain.dto.RentalSearchCondition;
import com.gamsung.domain.dto.RentalSlipListDto;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.gamsung.domain.QCustomer.*;
import static com.gamsung.domain.QRentalSlip.*;
import static com.querydsl.jpa.JPAExpressions.*;

public class RentalRepositoryImpl implements RentalRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public RentalRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<RentalSlipListDto> search() {
        return queryFactory
                .select(new QRentalSlipListDto(
                        rentalSlip.rentalNum,
                        ExpressionUtils.as(
                                select(customer.count())
                                        .from(customer)
                                        .where(customer.rentalNum.eq(rentalSlip.rentalNum)),
                                "customerCount"),
                        rentalSlip.deposit,
                        rentalSlip.receiver))
                .from(rentalSlip)
                .leftJoin(rentalSlip.customers, customer)
                .where(rentalSlip.rentalStatus.eq(RentalStatus.RECEIVED))
                .fetch();
    }
}

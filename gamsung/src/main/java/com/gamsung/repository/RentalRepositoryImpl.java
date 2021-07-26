package com.gamsung.repository;

import com.gamsung.domain.Customer;
import com.gamsung.domain.QCustomer;
import com.gamsung.domain.QRentalSlip;
import com.gamsung.domain.RentalStatus;
import com.gamsung.domain.dto.*;
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
    public List<RentalSlipListDto> searchRentalSlipList() {
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
                .where(rentalSlip.rentalStatus.eq(RentalStatus.RECEIVED))
                .fetch();
    }

    @Override
    public List<ReturnSlipListDto> searchReturnSlipList() {
        return queryFactory
                .select(new QReturnSlipListDto(
                        rentalSlip.rentalNum,
                        ExpressionUtils.as(
                                select(customer.count())
                                        .from(customer)
                                        .where(customer.rentalNum.eq(rentalSlip.rentalNum)),
                                "customerCount"),
                        rentalSlip.deposit,
                        rentalSlip.receiver,
                        rentalSlip.payment))
                .from(rentalSlip)
                .where(rentalSlip.rentalStatus.eq(RentalStatus.PAYED))
                .fetch();
    }

    @Override
    public List<AdminRentalSlipListDto> adminRentalSlipList() {
        return queryFactory
                .select(new QAdminRentalSlipListDto(
                        rentalSlip.rentalNum,
                        rentalSlip.receiver,
                        rentalSlip.payment,
                        rentalSlip.gamsung_pos,
                        rentalSlip.returner,
                        rentalSlip.createdDate
                ))
                .from(rentalSlip)
                .fetch();
    }


    @Override
    public AdminRentalSlipListDto adminRentalSlipDetail(String rentalNum) {
        return queryFactory
                .select(new QAdminRentalSlipListDto(
                        rentalSlip.rentalNum,
                        rentalSlip.receiver,
                        rentalSlip.payment,
                        rentalSlip.gamsung_pos,
                        rentalSlip.returner,
                        rentalSlip.createdDate
                ))
                .from(rentalSlip)
                .where(rentalSlip.rentalNum.eq(rentalNum))
                .fetchOne();
    }
}

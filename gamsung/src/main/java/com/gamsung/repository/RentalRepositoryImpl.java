package com.gamsung.repository;

import com.gamsung.domain.*;
import com.gamsung.domain.dto.*;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<RentalSlipListDto> keywordRentalSlipList(String customerName) {
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
                .where(rentalSlip.rentalStatus.eq(RentalStatus.RECEIVED),
                        customer.customerName.eq(customerName))
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

    @Override
    public long countRentalSlip(RentalStatus rentalStatus) {
        return queryFactory
                .selectFrom(rentalSlip)
                .where(rentalSlip.rentalStatus.eq(rentalStatus))
                .fetchCount();
    }

    @Override
    public long countTodayRentalSlip(String today) {
        return queryFactory
                .selectFrom(rentalSlip)
                .where(rentalSlip.rentalNum.contains(today))
                .fetchCount();
    }

    @Override
    public List<AdminRentalSlipListDto> adminRentalSlipByCustomerName(String customerName) {
        List<RentalSlip> rentalSlips = queryFactory.selectFrom(rentalSlip)
                .leftJoin(rentalSlip.customers, customer)
                .where(customer.customerName.eq(customerName))
                .fetchJoin().fetch();

        return rentalSlips.stream()
                .map(p -> new AdminRentalSlipListDto(p.getRentalNum(), p.getReceiver(), p.getPayment(), p.getGamsung_pos(), p.getReturner(), p.getCreatedDate()))
                .collect(Collectors.toList());

    }

    @Override
    public List<AdminRentalSlipListDto> adminRentalSlipByEnterDate(String enterDate) {
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
                .where(rentalSlip.rentalNum.contains(enterDate))
                .fetch();
    }
}

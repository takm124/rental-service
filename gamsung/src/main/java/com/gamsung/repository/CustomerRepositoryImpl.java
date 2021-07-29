package com.gamsung.repository;

import com.gamsung.domain.QCustomer;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import static com.gamsung.domain.QCustomer.*;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public CustomerRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public long countTodayCustomer(String today) {
        return queryFactory
                .selectFrom(customer)
                .where(customer.rentalNum.contains(today))
                .fetchCount();
    }
}

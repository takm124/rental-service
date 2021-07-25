package com.gamsung.repository;

import com.gamsung.domain.Place;
import com.gamsung.domain.QStaff;
import com.gamsung.domain.dto.QStaffDto;
import com.gamsung.domain.dto.StaffDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.gamsung.domain.QStaff.*;
import static org.thymeleaf.util.StringUtils.isEmpty;


public class StaffRepositoryImpl implements StaffRepositoryCustom{


    private final JPAQueryFactory queryFactory;

    public StaffRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<StaffDto> searchStaffList(Place place) {
        return queryFactory
                .select(new QStaffDto(staff.staffName, staff.phoneNumber))
                .from(staff)
                .where(staff.place.eq(place))
                .fetch();
    }
}

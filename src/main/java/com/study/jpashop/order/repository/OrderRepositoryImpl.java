package com.study.jpashop.order.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.jpashop.order.domain.Order;
import com.study.jpashop.order.domain.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.study.jpashop.delivery.domain.QDelivery.delivery;
import static com.study.jpashop.member.domain.QMember.*;
import static com.study.jpashop.order.domain.QOrder.order;
import static org.springframework.data.support.PageableExecutionUtils.getPage;
import static org.springframework.util.StringUtils.hasText;

public class OrderRepositoryImpl implements OrderRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Autowired
    EntityManager em;

    public OrderRepositoryImpl(EntityManager em) {
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Order> search(OrderSearch condition) {
        return queryFactory.select(order)
                .from(order)
                .where(
                        memberNameEq(condition.getMemberName()),
                        orderStatusEq(condition.getOrderStatus())
                )
                .fetch();
    }

    private BooleanExpression memberNameEq(String memberName) {
        return hasText(memberName) ? order.member.name.eq(memberName) : null;
    }

    private BooleanExpression orderStatusEq(OrderStatus orderStatus) {
        return orderStatus != null ? order.status.eq(orderStatus) : null;
    }

    @Override
    public Page<Order> searchPage(OrderSearch condition, Pageable pageable) {
        List<Order> content = queryFactory.select(order)
                .from(order)
                .where(
                        memberNameEq(condition.getMemberName()),
                        orderStatusEq(condition.getOrderStatus())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Order> countQuery = queryFactory
                .select(order)
                .from(order)
                .where(
                        memberNameEq(condition.getMemberName()),
                        orderStatusEq(condition.getOrderStatus())
                );
        return getPage(content, pageable, countQuery::fetchCount);
    }

    @Override
    public List<Order> findAllWithMemberDelivery() {
        return em.createQuery(
                "select o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d", Order.class
        ).getResultList();
    }

    @Override
    public List<Order> findAllWithMemberDeliveryQueryDsl() {

        List<Order> result = queryFactory.select(order)
                .from(order)
                .leftJoin(order.member, member)
                .fetchJoin()
                .leftJoin(order.delivery, delivery)
                .fetchJoin()
                .where()
                .fetch();
        return result;
    }



}

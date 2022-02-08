package com.study.jpashop.order.repository;

import com.study.jpashop.order.domain.Order;
import com.study.jpashop.order.repository.simplequery.OrderSimpleQueryRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends
        JpaRepository<Order, Long>,
        OrderRepositoryCustom,
        QuerydslPredicateExecutor<Order> ,
        OrderSimpleQueryRepository {

    public Order findTopById(Long id);


}

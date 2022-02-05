package com.study.jpashop.repository;

import com.study.jpashop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom, QuerydslPredicateExecutor<Order> {
    public Order findTopById(Long id);
}

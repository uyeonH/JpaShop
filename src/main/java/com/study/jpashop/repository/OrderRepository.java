package com.study.jpashop.repository;

import com.study.jpashop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

   // public List<Order> findAllByOrderSearch(OrderSearch orderSearch);
    public Order findTopById(Long id);

}

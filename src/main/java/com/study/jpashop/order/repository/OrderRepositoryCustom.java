package com.study.jpashop.order.repository;

import com.study.jpashop.order.domain.Order;
import com.study.jpashop.order.repository.simplequery.OrderSimpleQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepositoryCustom {
    List<Order> search(OrderSearch orderSearch);
    Page<Order> searchPage(OrderSearch orderSearch, Pageable pageable);
    List<Order> findAllWithMemberDelivery();

    List<Order> findAllWithMemberDeliveryQueryDsl();
    List<Order> findAllWithItem();

    List<Order> findAllWithMemberDelivery(int offset, int limit);
}

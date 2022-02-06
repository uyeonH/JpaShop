package com.study.jpashop.order.repository.simplequery;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringPath;
import com.study.jpashop.member.domain.Address;
import com.study.jpashop.member.domain.QAddress;
import com.study.jpashop.order.domain.Order;
import com.study.jpashop.order.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class OrderSimpleQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    @QueryProjection
    public OrderSimpleQueryDto(Order order) {
        orderId = order.getId();
        name = order.getMember().getName(); // LAZY 초기화
        orderDate = order.getOrderDate();
        orderStatus = order.getStatus();
        address = order.getDelivery().getAddress(); // LAZY 초기화
    }


}
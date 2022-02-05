package com.study.jpashop.order.repository;

import com.study.jpashop.order.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearch {
    private String memberName; // 회원 이름
    private OrderStatus orderStatus; // 주문 상태[ORDER, CANCEL]
}

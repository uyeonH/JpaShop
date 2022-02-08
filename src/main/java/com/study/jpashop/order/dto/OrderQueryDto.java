package com.study.jpashop.order.dto;

import com.study.jpashop.member.domain.Address;
import com.study.jpashop.order.domain.OrderStatus;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;
    private List<OrderItemQueryDto> orderItems;

    public OrderQueryDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address = address;
    }
}

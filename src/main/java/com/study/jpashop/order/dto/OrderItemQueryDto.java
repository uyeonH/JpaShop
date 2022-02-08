package com.study.jpashop.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class OrderItemQueryDto {
    private Long orderId;
    private String itemName;
    private int orderPrice;
    private int count;
}

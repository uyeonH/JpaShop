package com.study.jpashop.order.service;

import com.study.jpashop.delivery.domain.Delivery;
import com.study.jpashop.item.repository.ItemRepository;
import com.study.jpashop.member.domain.Member;
import com.study.jpashop.order.domain.Order;
import com.study.jpashop.order.domain.OrderItem;
import com.study.jpashop.item.domain.Item;
import com.study.jpashop.member.repository.MemberRepository;
import com.study.jpashop.order.repository.OrderRepository;
import com.study.jpashop.order.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //앤티티 조회
        Member member = memberRepository.findTopById(memberId);
        Item item = itemRepository.findTopById(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);
        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findTopById(orderId);
        order.cancel();
    }

    /**
     * 검색
     */
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.search(orderSearch);
    }
}

package com.study.jpashop.service;

import com.study.jpashop.member.domain.Address;
import com.study.jpashop.member.domain.Member;
import com.study.jpashop.order.domain.Order;
import com.study.jpashop.order.domain.OrderStatus;
import com.study.jpashop.item.domain.Book;
import com.study.jpashop.item.domain.Item;
import com.study.jpashop.exception.NotEnoughStockException;
import com.study.jpashop.order.service.OrderService;
import com.study.jpashop.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();
        Book book = createBook("jpa", 10000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //then
        Order getOrder = orderRepository.getOne(orderId);

        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(10000 * orderCount, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다.");
        assertEquals(8, book.getStockQuantity(), "주문 수량만큼 재고가 줄어야한다.");
    }


    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("jpa", 10000, 10);

        int orderCount = 11;

        //when then
        assertThrows(NotEnoughStockException.class, () ->
                orderService.order(member.getId(), item.getId(), orderCount)
        );
    }

    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = createMember();
        Book item = createBook("JPA", 1000, 10);
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findTopById(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "주문 취소시 상태는 CANCEL 이다.");
        assertEquals(10, item.getStockQuantity(), "주문 취소된 상품은 그만큼 재고가 증가해야한다.");

    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("member1");
        member.setAddress(new Address("서울", "강가", "11"));
        em.persist(member);
        return member;
    }
}
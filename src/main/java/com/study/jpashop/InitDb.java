package com.study.jpashop;

import com.study.jpashop.delivery.domain.Delivery;
import com.study.jpashop.item.domain.Book;
import com.study.jpashop.member.domain.Address;
import com.study.jpashop.member.domain.Member;
import com.study.jpashop.order.domain.Order;
import com.study.jpashop.order.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;


@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

            Book book = createBook("JPA1", 10000, 100);
            Book book2 = createBook("JPA2", 20000, 100);
            em.persist(book);
            em.persist(book2);

            OrderItem orderItem = OrderItem.createOrderItem(book, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem, orderItem2);
            em.persist(order);
        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Book createBook(String name, int price, int stockQuantity) {
            Book book = new Book();
            book.setName(name);
            book.setPrice(price);
            book.setStockQuantity(stockQuantity);
            return book;
        }

        public void dbInit2() {
            Member member = createMember("userB", "부산", "2", "2222");
            em.persist(member);

            Book book = createBook("SPRING 1", 20000, 200);
            Book book2 = createBook("SPRING 2", 30000, 300);

            em.persist(book);
            em.persist(book2);

            OrderItem orderItem = OrderItem.createOrderItem(book, 20000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(book, 30000, 4);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem, orderItem2);
            em.persist(order);
        }

        private Member createMember(String name, String city, String street
                , String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }
    }

}


package com.study.jpashop.order.repository.simplequery;

import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.study.jpashop.delivery.domain.QDelivery;
import com.study.jpashop.member.domain.QMember;
import com.study.jpashop.order.domain.QOrder;
import com.study.jpashop.order.repository.OrderRepositoryCustom;
import com.study.jpashop.order.repository.simplequery.QOrderSimpleQueryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

import static com.study.jpashop.delivery.domain.QDelivery.*;
import static com.study.jpashop.member.domain.QMember.member;
import static com.study.jpashop.order.domain.QOrder.order;

@RequiredArgsConstructor
public class OrderSimpleQueryRepositoryImpl implements OrderSimpleQueryRepository {
  //  private final JPAQueryFactory queryFactory;

    @Autowired
    EntityManager em;

//    public OrderSimpleQueryRepositoryImpl(EntityManager em) {
//        queryFactory = new JPAQueryFactory(em);
//    }

    @Override
    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery("select new com.study.jpashop.order.repository.simplequery.OrderSimpleQueryDto(o.id,m.name,o.orderDate,o.status,m.address)" +
                        " from Order o" +
                        " join o.member m" +
                        " join o.delivery d",
                OrderSimpleQueryDto.class).getResultList();
    }

//    @Override
//    public List<OrderSimpleQueryDto> findOrderDtosQuerydsl() {
//
//        return queryFactory.select(new QOrderSimpleQueryDto(
//                order.id,
//                member.name,
//                order.orderDate,
//                order.status,
//                member.address)
//        )
//                .from(order)
//                .join(order.member, member)
//                .fetchJoin()
//                .join(order.delivery, delivery)
//                .fetchJoin()
//                .fetch();
//    }
}

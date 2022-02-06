package com.study.jpashop.order.repository.simplequery;

import com.study.jpashop.order.repository.OrderRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public interface OrderSimpleQueryRepository {
    public List<OrderSimpleQueryDto> findOrderDtos();
   // public List<OrderSimpleQueryDto> findOrderDtosQuerydsl();

}

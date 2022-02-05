package com.study.jpashop.item.repository;

import com.study.jpashop.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository  extends JpaRepository<Item, Long> {
    public Item findTopById(Long id);
}

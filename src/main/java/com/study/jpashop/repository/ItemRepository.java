package com.study.jpashop.repository;

import com.study.jpashop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository  extends JpaRepository<Item, Long> {
    public Item findTopById(Long id);
}

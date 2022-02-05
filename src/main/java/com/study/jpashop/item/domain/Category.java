package com.study.jpashop.item.domain;

import com.study.jpashop.item.domain.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id @GeneratedValue
    @Column(name="category_id")
    private Long id;
    private String name;

   @OneToMany(mappedBy = "category")
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
        item.setCategory(this);
    }

}

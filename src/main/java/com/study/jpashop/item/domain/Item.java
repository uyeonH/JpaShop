package com.study.jpashop.item.domain;

import com.study.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // movie, album, book 한 테이블에
@DiscriminatorColumn(name = "dtype") // 구분자자
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    //==비즈니스 로직==//

    /**
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if (restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}

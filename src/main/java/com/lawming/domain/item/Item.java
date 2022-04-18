package com.lawming.domain.item;

import com.lawming.domain.member.Member;
import com.lawming.domain.Order;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String city;

    private Integer price;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUEST_MEMBER_ID")
    private Member owner; // 해당 아이템 소유자

    @OneToOne(mappedBy = "item",fetch = FetchType.LAZY)
    private Order order;

    // TODO : 복대리 외의 아이템 종류도 추가해야함
    @Enumerated(EnumType.STRING)
    private ItemType itemType = ItemType.SUB_AGENCY;

    public static Item createItem(String city, Integer price, Member owner) {
        Item item = new Item();

        item.setCity(city);
        item.setDueDate(LocalDateTime.now());
        item.setPrice(price);
        item.setOwner(owner);

        return item;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public Integer getPrice() {
        return price;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public Member getOwner() {
        return owner;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }
}

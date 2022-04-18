package com.lawming.domain;

import com.lawming.domain.item.Item;
import com.lawming.domain.member.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member requestMember;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    private LocalDateTime orderDateTime;

    public Long getId() {
        return id;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Member getRequestMember() {
        return requestMember;
    }

    public Item getItem() {
        return item;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    private void setRequestMember(Member requestMember) {
        this.requestMember = requestMember;
    }

    private void setItem(Item item) {
        this.item = item;
    }

    private void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public static Order createOrder(Member requestMember, Item item) {
        Order order = new Order();
        order.setOrderStatus(OrderStatus.WAIT);
        order.setOrderDateTime(LocalDateTime.now());
        order.setItem(item);
        order.setRequestMember(requestMember);
        return order;
    }
}

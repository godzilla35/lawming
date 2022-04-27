package com.lawming.domain.order;

import com.lawming.domain.item.Item;
import com.lawming.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    @Test
    public void createOrder() throws Exception {
        //given
        Member member = new Member();
        member.setId(1L);
        member.setName("Mdo");
        Item item = new Item();
        item.setId(2L);
        item.setPrice(10000);
        item.setCity("Seoul");

        //when
        Order order = Order.createOrder(member, item);
        //then

        Assertions.assertThat(order).isNotNull();
    }

}
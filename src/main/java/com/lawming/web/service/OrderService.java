package com.lawming.web.service;

import com.lawming.domain.item.Item;
import com.lawming.domain.member.Member;
import com.lawming.domain.Order;
import com.lawming.domain.repository.ItemRepository;
import com.lawming.domain.repository.MemberRepository;
import com.lawming.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Order order = Order.createOrder(member, item);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {

    }



}

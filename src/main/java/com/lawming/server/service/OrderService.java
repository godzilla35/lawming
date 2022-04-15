package com.lawming.server.service;

import com.lawming.server.domain.Item;
import com.lawming.server.domain.Member;
import com.lawming.server.domain.Order;
import com.lawming.server.repository.ItemRepository;
import com.lawming.server.repository.MemberRepository;
import com.lawming.server.repository.OrderRepository;
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

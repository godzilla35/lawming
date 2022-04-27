package com.lawming.web.service;

import com.lawming.domain.item.Item;
import com.lawming.domain.member.Member;
import com.lawming.domain.order.Order;
import com.lawming.domain.repository.ItemRepository;
import com.lawming.domain.repository.MemberRepository;
import com.lawming.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        List<Order> orders = orderRepository.findByItemIdMemberId(item.getId(), member.getId());
        if(orders.isEmpty()) {
            Order order = Order.createOrder(member, item);
            orderRepository.save(order);
            return order.getId();
        } else {
            if (orders.size() != 1) {
                log.info("invalid order exist!! itemId = {} memberId = {}", itemId, memberId);
                return -1L;
            } else {
                return orders.get(0).getId();
            }
        }
    }

    @Transactional
    public void cancelOrder(Long orderId) {

    }

    public Order findOne(Long orderId) {
        return orderRepository.findOne(orderId);
    }

    public List<Order> findAllByItemId(Long itemId) {
        return orderRepository.findByItemId(itemId);
    }

    public List<Order> findByItemIdMemberId(Long itemId, Long memberId) {
        return orderRepository.findByItemIdMemberId(itemId, memberId);
    }



}

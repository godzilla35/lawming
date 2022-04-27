package com.lawming.domain.repository;

import com.lawming.domain.member.Member;
import com.lawming.domain.order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    @Transactional
    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

    public List<Order> findByItemId(Long itemId) {

        return em.createQuery("select o from Order o where o.itemId like '%" + itemId + "'%").getResultList();
    }

    public List<Order> findByItemIdMemberId(Long paramItemId, Long paramMemberId) {
        return em.createQuery("select o from Order o where o.item.id = :itemId  and o.requestMember.id = :memberId")
                .setParameter("itemId", paramItemId)
                .setParameter("memberId", paramMemberId)
                .getResultList();

    }

    @Transactional
    public void update(Order orderParam) {
        Order getOrder = em.find(Order.class, orderParam.getId());
        getOrder.setOrderStatus(orderParam.getOrderStatus());
    }
}

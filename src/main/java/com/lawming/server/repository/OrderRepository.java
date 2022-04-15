package com.lawming.server.repository;

import com.lawming.server.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private EntityManager em;

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

    @Transactional
    public void update(Order orderParam) {
        Order getOrder = em.find(Order.class, orderParam.getId());
        getOrder.setOrderStatus(orderParam.getOrderStatus());
    }
}

package com.lawming.domain.repository;

import com.lawming.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemRepository {

    private final EntityManager em;

    @Transactional
    public void save(Item item) {
        em.persist(item);
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    @Transactional
    public void update(Long itemId, Item itemParam) {
        Item getItem = em.find(Item.class, itemId);
        getItem.setPrice(itemParam.getPrice());
        getItem.setCity(itemParam.getCity());
        getItem.setDueDate(itemParam.getDueDate());
    }

}

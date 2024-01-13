package com.farm_to_door.farm2door_API.Repository;

import org.springframework.stereotype.Repository;

import com.farm_to_door.farm2door_API.DAO.OrderItemDAO;
import com.farm_to_door.farm2door_API.Entity.OrderItem;

import jakarta.persistence.EntityManager;

@Repository
public class OrderItemRepository implements OrderItemDAO{

    private EntityManager entityManager;

    public OrderItemRepository(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    public void save(OrderItem orderItem) {
        entityManager.persist(orderItem);
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        entityManager.merge(orderItem);
    }

    @Override
    public OrderItem getOrderItem(Long lineItemId) {
        return entityManager.find(OrderItem.class, lineItemId);
    }
    
}

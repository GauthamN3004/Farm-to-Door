package com.farm_to_door.farm2door_API.Repository;

import org.springframework.stereotype.Repository;

import com.farm_to_door.farm2door_API.Entity.OrderStatus;

import jakarta.persistence.EntityManager;

@Repository
public class OrderStatusRepository {
    private EntityManager entityManager;

    public OrderStatusRepository(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    public OrderStatus getOrderStatusById(int statusId){
        return this.entityManager.find(OrderStatus.class, statusId);
    }
}

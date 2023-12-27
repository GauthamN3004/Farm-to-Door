package com.farm_to_door.farm2door_API.DAO;

import com.farm_to_door.farm2door_API.Entity.OrderItem;

public interface OrderItemDAO {
    void save(OrderItem orderItem);
    void updateOrderItem(OrderItem orderItem);
    OrderItem getOrderItem(Long id);
}

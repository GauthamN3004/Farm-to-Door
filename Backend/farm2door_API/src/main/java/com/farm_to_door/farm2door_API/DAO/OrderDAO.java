package com.farm_to_door.farm2door_API.DAO;

import java.util.List;

import com.farm_to_door.farm2door_API.Entity.Order;

public interface OrderDAO {
    void placeOrder(long customerId, String paymentMode);
    Order getOrderInfo(long orderId);
    void updateOrderInfo(Order order);
    List<Order> getCustomerOrders(long customerId);
}

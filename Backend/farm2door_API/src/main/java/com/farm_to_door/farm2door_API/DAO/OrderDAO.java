package com.farm_to_door.farm2door_API.DAO;

import java.util.List;

import com.farm_to_door.farm2door_API.Entity.Order;
import com.farm_to_door.farm2door_API.Entity.OrderItem;

public interface OrderDAO {
    void placeOrder(long customerId);
    Order getOrderInfo(long orderId);
    void updateOrderInfo(Order order);
    List<Order> getCustomerOrders(long customerId);
    List<OrderItem> getFarmerOrders(long farmerId);
}

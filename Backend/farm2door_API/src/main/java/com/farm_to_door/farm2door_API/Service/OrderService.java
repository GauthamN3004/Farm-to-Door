package com.farm_to_door.farm2door_API.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farm_to_door.farm2door_API.Entity.Order;
import com.farm_to_door.farm2door_API.Entity.OrderItem;
import com.farm_to_door.farm2door_API.Entity.OrderStatus;
import com.farm_to_door.farm2door_API.Repository.OrderItemRepository;
import com.farm_to_door.farm2door_API.Repository.OrderRepository;
import com.farm_to_door.farm2door_API.Repository.OrderStatusRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
    private OrderRepository orderRepository;
    private OrderStatusRepository orderStatusRepository;
    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(OrderRepository theOrderRepository, OrderStatusRepository theOrderStatusRepository, OrderItemRepository theOrderItemRepository){
        this.orderRepository = theOrderRepository;
        this.orderStatusRepository = theOrderStatusRepository;
        this.orderItemRepository = theOrderItemRepository;
    }

    public ResponseEntity<?> placeOrder(long customerId){
        try{
            orderRepository.placeOrder(customerId);
            return ResponseEntity.ok().body("Order Placed Successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Could not place order. Error - " + e.getMessage());
        }
    }

    public ResponseEntity<?> getCustomerOrders(long customerId){
        try{
            List<Order> orders = orderRepository.getCustomerOrders(customerId);
            return ResponseEntity.ok().body(orders);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Could not get customer orders. Error - " + e.getMessage());
        }
    }

    public ResponseEntity<?> getFarmerOrders(long farmerId){
        try{
            List<OrderItem> orders = orderRepository.getFarmerOrders(farmerId);
            return ResponseEntity.ok().body(orders);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Could not get farmer orders. Error - " + e.getMessage());
        }
    }

    public ResponseEntity<?> updateOrderStatus(long orderItemId, int statusId){
        OrderItem orderItem = orderItemRepository.getOrderItem(orderItemId);
        if(orderItem == null){
            return ResponseEntity.badRequest().body("Order not found. Order ID: " + orderItemId);
        }

        OrderStatus newOrderStatus = orderStatusRepository.getOrderStatusById(statusId);
        if(newOrderStatus == null){
            return ResponseEntity.badRequest().body("Order Status not found. Order Status ID: " + statusId);
        }

        try{
            orderItem.setOrderStatus(newOrderStatus);
            orderItemRepository.updateOrderItem(orderItem);
            return ResponseEntity.ok().body(orderItem);
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Could not update order items. Error: " + e.getMessage());
        }
    }
}

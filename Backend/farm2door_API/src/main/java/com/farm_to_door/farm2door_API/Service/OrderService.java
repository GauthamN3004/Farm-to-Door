package com.farm_to_door.farm2door_API.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farm_to_door.farm2door_API.Entity.Order;
import com.farm_to_door.farm2door_API.Entity.OrderItem;
import com.farm_to_door.farm2door_API.Repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository theOrderRepository){
        this.orderRepository = theOrderRepository;
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
}

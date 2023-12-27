package com.farm_to_door.farm2door_API.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.farm_to_door.farm2door_API.DAO.OrderDAO;
import com.farm_to_door.farm2door_API.Entity.Cart;
import com.farm_to_door.farm2door_API.Entity.Customer;
import com.farm_to_door.farm2door_API.Entity.Harvest;
import com.farm_to_door.farm2door_API.Entity.Order;
import com.farm_to_door.farm2door_API.Entity.OrderItem;

import jakarta.persistence.EntityManager;

@Repository
public class OrderRepository implements OrderDAO {

    private EntityManager entityManager;
    private CartRepository cartRepository;

    public OrderRepository(EntityManager theEntityManager, CartRepository theCartRepository){
        this.entityManager = theEntityManager;
        this.cartRepository = theCartRepository;
    }

    @Override
    public void placeOrder(long customerId, String paymentMode) {
        Order order = new Order();
        order.setCustomer(entityManager.find(Customer.class, customerId));
        order.setOrderDate(new Date());
        order.setOrderStatus("Active");
        order.setPaymentMode(paymentMode);
        List<Cart> cartItems = cartRepository.getCartForCustomer(customerId);
        int total_price = 0;

        for (Cart cartItem : cartItems){
            total_price += (cartItem.getQuantity() * cartItem.getHarvest().getPricePerQuantity());
        }
        order.setTotalPrice(total_price);

        entityManager.persist(order);

        for (Cart cartItem : cartItems){
            Harvest harvest = cartItem.getHarvest();

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setHarvest(harvest);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getQuantity() * harvest.getPricePerQuantity());

            entityManager.persist(orderItem);

            entityManager.remove(cartItem);
        }
    }

    @Override
    public Order getOrderInfo(long orderId) {
        return entityManager.find(Order.class, orderId);
    }

    @Override
    public void updateOrderInfo(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOrderInfo'");
    }

    @Override
    public List<Order> getCustomerOrders(long customerId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCustomerOrders'");
    }
    
}

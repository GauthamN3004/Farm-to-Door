package com.farm_to_door.farm2door_API.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.farm_to_door.farm2door_API.DAO.OrderDAO;
import com.farm_to_door.farm2door_API.Entity.Cart;
import com.farm_to_door.farm2door_API.Entity.Customer;
import com.farm_to_door.farm2door_API.Entity.Harvest;
import com.farm_to_door.farm2door_API.Entity.Order;
import com.farm_to_door.farm2door_API.Entity.OrderItem;
import com.farm_to_door.farm2door_API.Entity.OrderStatus;

import jakarta.persistence.EntityManager;

@Repository
public class OrderRepository implements OrderDAO {

    private EntityManager entityManager;
    private CartRepository cartRepository;
    private OrderStatusRepository orderStatusRepository;

    public OrderRepository(EntityManager theEntityManager, CartRepository theCartRepository, OrderStatusRepository theOrderStatusRepository){
        this.entityManager = theEntityManager;
        this.cartRepository = theCartRepository;
        this.orderStatusRepository = theOrderStatusRepository;
    }

    @Override
    public void placeOrder(long customerId) {
        Order order = new Order();
        order.setCustomer(entityManager.find(Customer.class, customerId));
        order.setOrderDate(new Date());
        // order.setOrderStatus(orderStatusRepository.getOrderStatusById(1));
        // order.setPaymentMode(paymentMode);
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
            orderItem.setOrderStatus(orderStatusRepository.getOrderStatusById(1));

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
        String query = "SELECT o FROM Order o WHERE o.customer.customerId = :customerId ORDER BY o.orderId DESC";
        return entityManager.createQuery(query, Order.class)
            .setParameter("customerId", customerId)
            .getResultList();
    }

    @Override
    public List<OrderItem> getFarmerOrders(long farmerId) {
        String query = "SELECT o FROM OrderItem o WHERE o.harvest.farmer.farmerId = :farmerId ORDER BY o.lineItemId DESC";
        List<OrderItem> result = entityManager.createQuery(query, OrderItem.class)
                .setParameter("farmerId", farmerId)
                .getResultList();
        return result;
    }

    public void updateOrderStatus(Order order){
        entityManager.merge(order);
    }
}

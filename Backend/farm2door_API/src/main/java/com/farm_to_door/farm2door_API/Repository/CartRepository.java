package com.farm_to_door.farm2door_API.Repository;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.farm_to_door.farm2door_API.DAO.CartDAO;
import com.farm_to_door.farm2door_API.DTO.CartDTO;
import com.farm_to_door.farm2door_API.Entity.Cart;
import com.farm_to_door.farm2door_API.Entity.Customer;
import com.farm_to_door.farm2door_API.Entity.Harvest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class CartRepository implements CartDAO {

    private EntityManager entityManager;
    
    public CartRepository(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }
    
    @Override
    @Transactional
    public void addToCart(CartDTO cartItem) throws Exception{
        
        Harvest harvest = entityManager.find(Harvest.class, cartItem.getHarvestId(), LockModeType.PESSIMISTIC_WRITE);

        if (harvest.getQuantity() >= cartItem.getQuantity()) {

            harvest.setQuantity(harvest.getQuantity() - cartItem.getQuantity());

            Cart newCartItem = new Cart();
            newCartItem.setCustomer(entityManager.find(Customer.class, cartItem.getCustomerId()));
            newCartItem.setHarvest(harvest);
            newCartItem.setQuantity(cartItem.getQuantity());

            entityManager.persist(newCartItem);
        } 
        else {
            throw new Exception("Quantity in cart more than the available quantity.");
        }
    }

    @Override
    @Transactional
    public void deleteFromCart(long cartItemId) throws Exception {
        Cart cartItem = entityManager.find(Cart.class, cartItemId);

        if (cartItem != null) {
            long harvestId = cartItem.getHarvest().getHarvestId();
            Harvest harvest = entityManager.find(Harvest.class, harvestId, LockModeType.PESSIMISTIC_WRITE);
            if (harvest != null) {
                entityManager.remove(cartItem);
                harvest.setQuantity(harvest.getQuantity() + cartItem.getQuantity());
            } 
            else {
                entityManager.getTransaction().rollback();
                throw new Exception("Harvest not found for CartItem with ID: " + cartItemId);
            }
        } 
        else {
            throw new Exception("CartItem not found with ID: " + cartItemId);
        }
    }

    @Override
    public Cart getCartItem(long cartId) {
        return entityManager.find(Cart.class, cartId);
    }

    @Override
    public List<Cart> getCartForCustomer(long customerId) {
        String jpql = "SELECT c FROM Cart c WHERE c.customer.customerId = :customerId";
        TypedQuery<Cart> query = entityManager.createQuery(jpql, Cart.class);
        query.setParameter("customerId", customerId);
        return query.getResultList();
    }
    
}

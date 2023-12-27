package com.farm_to_door.farm2door_API.DAO;

import java.util.List;

import com.farm_to_door.farm2door_API.DTO.CartDTO;
import com.farm_to_door.farm2door_API.Entity.Cart;

public interface CartDAO {
    void addToCart(CartDTO cartItem) throws Exception;
    void deleteFromCart(long cartItemId) throws Exception;
    // void updateCartItem(Cart cartItem);
    Cart getCartItem(long cartId);
    List<Cart> getCartForCustomer(long customer_id);
}

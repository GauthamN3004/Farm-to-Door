package com.farm_to_door.farm2door_API.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.farm_to_door.farm2door_API.DTO.CartDTO;
import com.farm_to_door.farm2door_API.Entity.Cart;
import com.farm_to_door.farm2door_API.Entity.Customer;
import com.farm_to_door.farm2door_API.Entity.Farmer;
import com.farm_to_door.farm2door_API.Repository.CartRepository;
import com.farm_to_door.farm2door_API.Repository.CustomerRepository;
import com.farm_to_door.farm2door_API.Repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private CartRepository cartRepository;
    private OrderRepository orderRepository;

    @Autowired
    public CustomerService(CustomerRepository theCustomerRepository, CartRepository theCartRepository, OrderRepository theOrderRepository){
        this.customerRepository = theCustomerRepository;
        this.cartRepository = theCartRepository;
        this.orderRepository = theOrderRepository;
    }

    public ResponseEntity<?> getCustomerInfo(long customerId){
        Customer customer = customerRepository.getCustomerInfo(customerId);
        if(customer == null){
            return ResponseEntity.badRequest().body("Cannot find customer with ID:" + customerId);
        }
        return ResponseEntity.ok(customer);
    }

    @Transactional
    public ResponseEntity<?> deleteCustomer(long customerId){
        Customer customer = customerRepository.getCustomerInfo(customerId);
        if(customer == null){
            return ResponseEntity.badRequest().body("Cannot find customer with ID:" + customerId);
        }
        customer.setEnabled(false);
        
        try{
            customerRepository.updateCustomerInfo(customer);
            return ResponseEntity.ok("Customer Deleted Successfully");
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Could not delete farmer! Error - " + e);
        }
    }

    @Transactional
    public ResponseEntity<?> addItemToCart(CartDTO cartItem){
        try{
            cartRepository.addToCart(cartItem);
            return ResponseEntity.ok("Item Added to Cart Successfully");
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Could not add item to cart. Error - " + e);
        }
    }

    @Transactional
    public ResponseEntity<?> deleteItemInCart(long cartItemId){
        try{
            cartRepository.deleteFromCart(cartItemId);
            return ResponseEntity.ok("Item Removed from Cart Successfully");
        } catch(Exception e) {
            return ResponseEntity.badRequest().body("Could not remove item from cart. Error - " + e);
        }
    }

    public ResponseEntity<?> viewCart(long customerId){
        try{
            List<Cart> cartItems = cartRepository.getCartForCustomer(customerId);
            return ResponseEntity.ok(cartItems);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Could not fetch cart items. Error - " + e);
        }
    }
    
}

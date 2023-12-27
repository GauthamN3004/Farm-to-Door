package com.farm_to_door.farm2door_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm_to_door.farm2door_API.DTO.CartDTO;
import com.farm_to_door.farm2door_API.Service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService theCustomerService){
        this.customerService = theCustomerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerInfo(@PathVariable long customerId){
        return customerService.getCustomerInfo(customerId);
    }

    @GetMapping("/{customerId}/cart")
    public ResponseEntity<?> viewCart(@PathVariable long customerId){        
        return customerService.viewCart(customerId);
    }

    @PostMapping("/{customerId}/cart")
    public ResponseEntity<?> addHarvestToCart(@PathVariable long customerId, @RequestBody CartDTO cartItem){        
        return customerService.addItemToCart(cartItem);
    }

    @DeleteMapping("/{customerId}/cart/{cartItemId}")
    public ResponseEntity<?> deleteHarvestInCart(@PathVariable long customerId, @PathVariable long cartItemId){        
        return customerService.deleteItemInCart(cartItemId);
    }

    @PostMapping("/{customerId}/order")
    public ResponseEntity<?> placeOrder(@PathVariable long customerId){
        return null;

    }
}

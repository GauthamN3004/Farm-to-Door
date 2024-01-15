package com.farm_to_door.farm2door_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.farm_to_door.farm2door_API.DTO.CartDTO;
import com.farm_to_door.farm2door_API.Service.CustomerService;
import com.farm_to_door.farm2door_API.Service.HarvestService;
import com.farm_to_door.farm2door_API.Service.OrderService;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin
public class CustomerController {
    private CustomerService customerService;
    private HarvestService harvestService;
    private OrderService orderService;

    @Autowired
    public CustomerController(CustomerService theCustomerService, HarvestService theHarvestService, OrderService theOrderService){
        this.customerService = theCustomerService;
        this.harvestService = theHarvestService;
        this.orderService = theOrderService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getCustomerInfo(@PathVariable long customerId){
        return customerService.getCustomerInfo(customerId);
    }

    @GetMapping("/{customerId}/shop")
    public ResponseEntity<?> getAllHarvest(
        @PathVariable long customerId,
        @RequestParam(name = "page", required = false) Integer page,
        @RequestParam(name = "pageSize", required = false) Integer pageSize,
        @RequestParam(name = "category", required = false) String category,
        @RequestParam(name = "minPrice", required = false) Integer minPrice,
        @RequestParam(name = "maxPrice", required = false) Integer maxPrice
    ){
        return harvestService.getAllHarvestsPaginated(page, pageSize, category, minPrice, maxPrice);
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
        return orderService.placeOrder(customerId);
    }

    @GetMapping("/{customerId}/order")
    public ResponseEntity<?> getCustomerOrders(@PathVariable long customerId){
        // return ResponseEntity.ok("Controller");
        return orderService.getCustomerOrders(customerId);
    }

    @PostMapping("/{customerId}/update-items-status")
    public ResponseEntity<?> updateOrderItemsStatus(@RequestParam long orderItemId, @RequestParam int statusId){
        return orderService.updateOrderStatus(orderItemId, statusId);
    }
}

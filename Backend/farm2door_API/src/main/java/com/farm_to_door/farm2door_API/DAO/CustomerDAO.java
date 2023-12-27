package com.farm_to_door.farm2door_API.DAO;

import com.farm_to_door.farm2door_API.Entity.Customer;

public interface CustomerDAO {
    void save(Customer customer);
    Customer getCustomerInfo(Long customerId);
    void updateCustomerInfo(Customer customer);
}

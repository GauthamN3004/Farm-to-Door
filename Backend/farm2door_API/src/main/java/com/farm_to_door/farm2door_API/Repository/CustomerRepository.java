package com.farm_to_door.farm2door_API.Repository;

import org.springframework.stereotype.Repository;

import com.farm_to_door.farm2door_API.DAO.CustomerDAO;
import com.farm_to_door.farm2door_API.Entity.Customer;

import jakarta.persistence.EntityManager;

@Repository
public class CustomerRepository implements CustomerDAO {

    private EntityManager entityManager;

    public CustomerRepository(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    public void save(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public Customer getCustomerInfo(Long customerId) {
        return entityManager.find(Customer.class, customerId);
    }

    @Override
    public void updateCustomerInfo(Customer customer) {
        entityManager.merge(customer);
    }

}

package com.farm_to_door.farm2door_API.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_status")
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private int statusId;

    @Column(name = "customer_status")
    private String customerStatus;

    @Column(name = "farmer_status")
    private String farmerStatus;

    // Default constructor
    public OrderStatus() {
    }

    // Parameterized constructor
    public OrderStatus(String customerStatus, String farmerStatus) {
        this.customerStatus = customerStatus;
        this.farmerStatus = farmerStatus;
    }

    // Getters and Setters

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getFarmerStatus() {
        return farmerStatus;
    }

    public void setFarmerStatus(String farmerStatus) {
        this.farmerStatus = farmerStatus;
    }
}
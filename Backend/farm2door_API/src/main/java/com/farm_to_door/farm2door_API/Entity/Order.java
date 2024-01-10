package com.farm_to_door.farm2door_API.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import com.farm_to_door.farm2door_API.Entity.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnoreProperties("order")
    private List<OrderItem> orderItems;

    @Column(name = "order_date", nullable = false)
    private Date orderDate;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "payment_mode", length = 100)
    private String paymentMode;

    // @ManyToOne
    // @JoinColumn(name = "order_status", referencedColumnName = "status_id")
    // private OrderStatus orderStatus;
    
    // Constructors

    public Order() {
        // Default constructor
    }

    public Order(Customer customer, Date orderDate, Integer totalPrice, String paymentMode
        // , OrderStatus orderStatus
    ) {
        this.customer = customer;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.paymentMode = paymentMode;
        // this.orderStatus = orderStatus;
    }

    // Getters and Setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    // public OrderStatus getOrderStatus() {
    //     return orderStatus;
    // }

    // public void setOrderStatus(OrderStatus orderStatus) {
    //     this.orderStatus = orderStatus;
    // }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customer=" + customer +
                ", orderDate=" + orderDate +
                ", totalPrice=" + totalPrice +
                ", paymentMode='" + paymentMode + '\'' +
                // ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}


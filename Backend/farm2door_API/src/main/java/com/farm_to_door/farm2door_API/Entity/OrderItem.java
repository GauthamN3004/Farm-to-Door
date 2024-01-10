package com.farm_to_door.farm2door_API.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_item_id")
    private Long lineItemId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @JsonIgnoreProperties("orderItems")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "harvest_id", referencedColumnName = "harvest_id")
    private Harvest harvest;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "order_status", referencedColumnName = "status_id")
    private OrderStatus orderStatus;

    // Constructors

    public OrderItem() {
        // Default constructor
    }

    public OrderItem(Order order, Harvest harvest, Integer quantity, Integer price, OrderStatus orderStatus) {
        this.order = order;
        this.harvest = harvest;
        this.quantity = quantity;
        this.price = price;
        this.orderStatus  = orderStatus;
    }

    // Getters and Setters

    public Long getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(Long lineItemId) {
        this.lineItemId = lineItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Harvest getHarvest() {
        return harvest;
    }

    public void setHarvest(Harvest harvest) {
        this.harvest = harvest;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "lineItemId=" + lineItemId +
                ", order=" + order +
                ", harvest=" + harvest +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
package com.farm_to_door.farm2door_API.DTO;

public class CartDTO {
    private long cartId;
    private long customerId;
    private long harvestId;
    private int quantity;

    // Constructors

    public CartDTO() {
        // Default constructor
    }

    public CartDTO(long cartId, long customerId, long harvestId, int quantity) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.harvestId = harvestId;
        this.quantity = quantity;
    }

    // Getters and Setters

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getHarvestId() {
        return harvestId;
    }

    public void setHarvestId(long harvestId) {
        this.harvestId = harvestId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "cartId=" + cartId +
                ", customerId=" + customerId +
                ", harvestId=" + harvestId +
                ", quantity=" + quantity +
                '}';
    }
}


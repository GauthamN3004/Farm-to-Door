package com.farm_to_door.farm2door_API.DTO;

import java.util.Date;

public class HarvestDTO {
    private long harvestId;
    private String cropName;
    private long farmerId;
    private Date harvestDate;
    private Date expiryDate;
    private long quantity;
    private String units;
    private int pricePerQuantity;
    private int smallestUnitSize;
    private int categoryId;
    private String imageUrl;
    private boolean active;

    // Constructors

    public HarvestDTO() {
    }

    public HarvestDTO(long harvestId, String cropName, long farmerId, Date harvestDate, Date expiryDate, long quantity,
                      String units, int pricePerQuantity, int smallestUnitSize, int categoryId, String imageUrl, boolean active) {
        this.harvestId = harvestId;
        this.cropName = cropName;
        this.farmerId = farmerId;
        this.harvestDate = harvestDate;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.units = units;
        this.pricePerQuantity = pricePerQuantity;
        this.smallestUnitSize = smallestUnitSize;
        this.categoryId = categoryId;
        this.imageUrl = imageUrl;
        this.active = active;
    }

    // Getters

    public long getHarvestId() {
        return harvestId;
    }

    public String getCropName() {
        return cropName;
    }

    public long getFarmerId() {
        return farmerId;
    }

    public Date getHarvestDate() {
        return harvestDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public long getQuantity() {
        return quantity;
    }

    public String getUnits() {
        return units;
    }

    public int getPricePerQuantity() {
        return pricePerQuantity;
    }

    public int getSmallestUnitSize() {
        return smallestUnitSize;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isActive() {
        return active;
    }

    // Setters

    public void setHarvestId(long harvestId) {
        this.harvestId = harvestId;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public void setFarmerId(long farmerId) {
        this.farmerId = farmerId;
    }

    public void setHarvestDate(Date harvestDate) {
        this.harvestDate = harvestDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setPricePerQuantity(int pricePerQuantity) {
        this.pricePerQuantity = pricePerQuantity;
    }

    public void setSmallestUnitSize(int smallestUnitSize) {
        this.smallestUnitSize = smallestUnitSize;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

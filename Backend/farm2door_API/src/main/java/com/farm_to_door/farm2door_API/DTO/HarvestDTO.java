package com.farm_to_door.farm2door_API.DTO;

import java.util.Date;

public class HarvestDTO {
    private Long harvestId;
    private String cropName;
    private Long farmerId;
    private Date harvestDate;
    private Date expiryDate;
    private Long quantity;
    private String units;
    private Integer pricePerQuantity;
    private Integer smallestUnitSize;
    private String imageUrl;
    private boolean active;

    // Constructors

    public HarvestDTO() {
    }

    public HarvestDTO(Long harvestId, String cropName, Long farmerId, Date harvestDate, Date expiryDate, Long quantity,
                      String units, Integer pricePerQuantity, Integer smallestUnitSize, String imageUrl, boolean active) {
        this.harvestId = harvestId;
        this.cropName = cropName;
        this.farmerId = farmerId;
        this.harvestDate = harvestDate;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.units = units;
        this.pricePerQuantity = pricePerQuantity;
        this.smallestUnitSize = smallestUnitSize;
        this.imageUrl = imageUrl;
        this.active = active;
    }

    // Getters

    public Long getHarvestId() {
        return harvestId;
    }

    public String getCropName() {
        return cropName;
    }

    public Long getFarmerId() {
        return farmerId;
    }

    public Date getHarvestDate() {
        return harvestDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public Long getQuantity() {
        return quantity;
    }

    public String getUnits() {
        return units;
    }

    public Integer getPricePerQuantity() {
        return pricePerQuantity;
    }

    public Integer getSmallestUnitSize() {
        return smallestUnitSize;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isActive() {
        return active;
    }

    // Setters

    public void setHarvestId(Long harvestId) {
        this.harvestId = harvestId;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public void setFarmerId(Long farmerId) {
        this.farmerId = farmerId;
    }

    public void setHarvestDate(Date harvestDate) {
        this.harvestDate = harvestDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setPricePerQuantity(Integer pricePerQuantity) {
        this.pricePerQuantity = pricePerQuantity;
    }

    public void setSmallestUnitSize(Integer smallestUnitSize) {
        this.smallestUnitSize = smallestUnitSize;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

package com.farm_to_door.farm2door_API.Entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "harvest")
public class Harvest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "harvest_id")
    private Long harvestId;

    @Column(name = "crop_name")
    private String cropName;

    @ManyToOne
    // @JsonIgnore
    @JoinColumn(name = "farmer_id", nullable = false)
    private Farmer farmer;

    @Column(name = "harvest_date", nullable = false)
    private Date harvestDate;

    @Column(name = "expiry_date", nullable = false)
    private Date expiryDate;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "units")
    private String units;

    @Column(name = "price_per_quantity", nullable = false)
    private Integer pricePerQuantity;

    @Column(name = "smallest_unit_size", nullable = false)
    private Integer smallestUnitSize;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "active")
    private Boolean active;

    // Default constructor
    public Harvest() {
    }

    // Parameterized constructor
    public Harvest(String cropName, Farmer farmer, Date harvestDate, Date expiryDate,
                   Long quantity, String units, Integer pricePerQuantity,
                   Integer smallestUnitSize, String imageUrl, Boolean active) {
        this.cropName = cropName;
        this.farmer = farmer;
        this.harvestDate = harvestDate;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.units = units;
        this.pricePerQuantity = pricePerQuantity;
        this.smallestUnitSize = smallestUnitSize;
        this.imageUrl = imageUrl;
        this.active = active;
    }

    // Getters and setters

    public Long getHarvestId() {
        return harvestId;
    }

    public void setHarvestId(Long harvestId) {
        this.harvestId = harvestId;
    }

    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public Date getHarvestDate() {
        return harvestDate;
    }

    public void setHarvestDate(Date harvestDate) {
        this.harvestDate = harvestDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Integer getPricePerQuantity() {
        return pricePerQuantity;
    }

    public void setPricePerQuantity(Integer pricePerQuantity) {
        this.pricePerQuantity = pricePerQuantity;
    }

    public Integer getSmallestUnitSize() {
        return smallestUnitSize;
    }

    public void setSmallestUnitSize(Integer smallestUnitSize) {
        this.smallestUnitSize = smallestUnitSize;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Harvest{" +
                "harvestId=" + harvestId +
                ", cropName='" + cropName + '\'' +
                ", farmer=" + farmer +
                ", harvestDate=" + harvestDate +
                ", expiryDate=" + expiryDate +
                ", quantity=" + quantity +
                ", units='" + units + '\'' +
                ", pricePerQuantity=" + pricePerQuantity +
                ", smallestUnitSize=" + smallestUnitSize +
                ", imageUrl='" + imageUrl + '\'' +
                ", active='" + active + '\'' +
                '}';
    }
}
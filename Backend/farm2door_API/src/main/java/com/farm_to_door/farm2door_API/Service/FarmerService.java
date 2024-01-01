package com.farm_to_door.farm2door_API.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.farm_to_door.farm2door_API.DTO.HarvestDTO;
import com.farm_to_door.farm2door_API.Entity.Farmer;
import com.farm_to_door.farm2door_API.Entity.Harvest;
import com.farm_to_door.farm2door_API.Repository.FarmerRepository;
import com.farm_to_door.farm2door_API.Repository.HarvestRepository;

import jakarta.transaction.Transactional;

@Service
public class FarmerService {
    
    private FarmerRepository farmerRepository;
    private HarvestRepository harvestRepository;

    @Autowired
    public FarmerService(FarmerRepository theFarmerRepository, HarvestRepository theHarvestRepository){
        this.farmerRepository = theFarmerRepository;
        this.harvestRepository = theHarvestRepository;
    }

    public ResponseEntity<?> getFarmerInfo(long farmerId){
        Farmer farmer = farmerRepository.getFarmerInfo(farmerId);
        if(farmer == null){
            return ResponseEntity.badRequest().body("Cannot find farmer with ID:" + farmerId);
        }
        return ResponseEntity.ok(farmer);
    }

    public ResponseEntity<?> deleteFarmer(long farmerId){
        Farmer farmer = farmerRepository.getFarmerInfo(farmerId);
        if(farmer == null){
            return ResponseEntity.badRequest().body("Cannot find farmer with ID:" + farmerId);
        }
        farmer.setEnabled(false);
        
        try{
            farmerRepository.updateFarmerInfo(farmer);
            return ResponseEntity.ok("Farmer Deleted Successfully");
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Could not delete farmer! Error - " + e);
        }
    }

    @Transactional
    public ResponseEntity<?> addFarmerHarvest(HarvestDTO harvestDTO, long farmerId){
        Farmer farmer = farmerRepository.getFarmerInfo(farmerId);
        if(farmer == null){
            return ResponseEntity.badRequest().body("Farmer not found. ID: " + farmerId);
        }
        
        Harvest harvest = new Harvest();
        harvest.setCropName(harvestDTO.getCropName());
        harvest.setFarmer(farmer);
        harvest.setHarvestDate(harvestDTO.getHarvestDate());
        harvest.setExpiryDate(harvestDTO.getExpiryDate());
        harvest.setQuantity(harvestDTO.getQuantity());
        harvest.setUnits(harvestDTO.getUnits());
        harvest.setPricePerQuantity(harvestDTO.getPricePerQuantity());
        harvest.setSmallestUnitSize(harvestDTO.getSmallestUnitSize());
        harvest.setImageUrl(harvestDTO.getImageUrl());
        harvest.setActive(true);

        try{
            harvestRepository.save(harvest);
            return ResponseEntity.ok("Harvest Added Successfully");
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Could not add harvest! Error - " + e);
        }
    }


    @Transactional
    public ResponseEntity<?> deleteHarvest(long farmerId, long harvestId){
        Harvest harvest = harvestRepository.getHarvestById(harvestId);
        
        if(harvest == null || harvest.getFarmer().getFarmerId() != farmerId || harvest.isActive() == false ){
            return ResponseEntity.badRequest().body("Could not delete Harvest ID: " + harvestId);
        }

        harvest.setActive(false);

        try{
            harvestRepository.updateHarvestInfo(harvest);
            return ResponseEntity.ok("Harvest Deleted Successfully");
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Could not update harvest! Error - " + e);
        }
    }
}

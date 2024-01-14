package com.farm_to_door.farm2door_API.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farm_to_door.farm2door_API.Entity.Farmer;
import com.farm_to_door.farm2door_API.Entity.Harvest;
import com.farm_to_door.farm2door_API.Entity.HarvestCategory;
import com.farm_to_door.farm2door_API.Repository.FarmerRepository;
import com.farm_to_door.farm2door_API.Repository.HarvestCategoryRepository;
import com.farm_to_door.farm2door_API.Repository.HarvestRepository;

@Service
public class HarvestService {

    private HarvestRepository harvestRepository;
    private FarmerRepository farmerRepository;
    private HarvestCategoryRepository harvestCategoryRepository;

    @Autowired
    public HarvestService (HarvestRepository theHarvestRepository, FarmerRepository theFarmerRepository, HarvestCategoryRepository theHarvestCategoryRepository){
        this.harvestRepository = theHarvestRepository;
        this.farmerRepository = theFarmerRepository;
        this.harvestCategoryRepository = theHarvestCategoryRepository;
    }

    public ResponseEntity<?> getFarmerHarvestsPaginated(long farmerId, int page, int pageSize){
        Farmer farmer = farmerRepository.getFarmerInfo(farmerId);
        if(farmer == null){
            return ResponseEntity.badRequest().body("Farmer not found. ID: " + farmerId);
        }
        List<Harvest> farmerHarvestPaginated = harvestRepository.getFarmerHarvestsPaginated(farmerId, page, pageSize);
        return ResponseEntity.ok(farmerHarvestPaginated);
    }

    public ResponseEntity<?> getFarmerHarvest(long farmerId){
        Farmer farmer = farmerRepository.getFarmerInfo(farmerId);
        if(farmer == null){
            return ResponseEntity.badRequest().body("Farmer not found. ID: " + farmerId);
        }
        List<Harvest> farmerHarvest = harvestRepository.getFarmerHarvests(farmerId);
        return ResponseEntity.ok(farmerHarvest);
    }

    public ResponseEntity<?> getAllHarvestsPaginated(int page, int pageSize){
        try{
            List<Harvest> farmerHarvestPaginated = harvestRepository.getAllHarvestsPaginated(page, pageSize);
            return ResponseEntity.ok(farmerHarvestPaginated);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getAllCategories(){
        try{
            List<HarvestCategory> result = harvestCategoryRepository.getAllCategories();
            return ResponseEntity.ok().body(result);
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Could not get harvest categories! Error - " + e);
        }
    }

}

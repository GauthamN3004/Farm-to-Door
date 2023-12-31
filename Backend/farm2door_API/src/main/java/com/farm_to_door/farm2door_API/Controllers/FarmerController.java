package com.farm_to_door.farm2door_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farm_to_door.farm2door_API.DTO.HarvestDTO;
import com.farm_to_door.farm2door_API.Entity.Harvest;
import com.farm_to_door.farm2door_API.Service.FarmerService;
import com.farm_to_door.farm2door_API.Service.HarvestService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/farmer")
@CrossOrigin
public class FarmerController {

    private FarmerService farmerService;
    private HarvestService harvestService;
    private static final Logger logger = LoggerFactory.getLogger(FarmerController.class);
    
    @Autowired
    public FarmerController(FarmerService theFarmerService, HarvestService theHarvestService){
        this.farmerService = theFarmerService;
        this.harvestService = theHarvestService;
    }

    @GetMapping("/{farmerId}")
    public ResponseEntity<?> getFarmerInfo(@PathVariable Long farmerId){
        return farmerService.getFarmerInfo(farmerId);
    }

    @PostMapping("/{farmerId}/harvest")
    public ResponseEntity<?> addFarmerHarvest(@RequestBody HarvestDTO harvestDTO, @PathVariable Long farmerId){
        return farmerService.addFarmerHarvest(harvestDTO, farmerId);
    }

    @GetMapping("/{farmerId}/harvest")
    public ResponseEntity<?> getFarmerHarvest(@PathVariable Long farmerId){
        try{
            return farmerService.getFarmerHarvest(farmerId);
        } catch(Exception e){
            return ResponseEntity.badRequest().body(e);
        }
    }

    @DeleteMapping("/{farmerId}/harvest/{harvestId}")
    public ResponseEntity<?> deleteFarmerHarvest(@PathVariable Long farmerId, @PathVariable Long harvestId){
        return farmerService.deleteHarvest(farmerId, harvestId);
    }
}



package com.farm_to_door.farm2door_API.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farm_to_door.farm2door_API.Repository.HarvestRepository;

@Service
public class HarvestService {

    private HarvestRepository harvestRepository;

    @Autowired
    public HarvestService (HarvestRepository theHarvestRepository){
        this.harvestRepository = theHarvestRepository;
    }
}

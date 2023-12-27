package com.farm_to_door.farm2door_API.DAO;

import java.util.List;

import com.farm_to_door.farm2door_API.Entity.Harvest;

public interface HarvestDAO {
    List<Harvest> getFarmerHarvests(Long farmer_id);
    Harvest getHarvestById(Long harvest_id);
    void save(Harvest harvest);
    void updateHarvestInfo(Harvest harvest);
}

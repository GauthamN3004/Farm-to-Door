package com.farm_to_door.farm2door_API.DAO;

import com.farm_to_door.farm2door_API.Entity.Farmer;

public interface FarmerDAO {
    void save(Farmer farmer);
    Farmer getFarmerInfo(Long farmerId);
    void updateFarmerInfo(Farmer farmer);
}

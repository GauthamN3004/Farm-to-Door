package com.farm_to_door.farm2door_API.Repository;

import org.springframework.stereotype.Repository;

import com.farm_to_door.farm2door_API.DAO.FarmerDAO;
import com.farm_to_door.farm2door_API.Entity.Farmer;

import jakarta.persistence.EntityManager;

@Repository
public class FarmerRepository implements FarmerDAO {
    
    private EntityManager entityManager;

    public FarmerRepository(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    public Farmer getFarmerInfo(Long farmerId){
        return entityManager.find(Farmer.class, farmerId);
    }

    @Override
    public void save(Farmer farmer) {
        entityManager.persist(farmer);
    }

    @Override
    public void updateFarmerInfo(Farmer farmer) {
        entityManager.merge(farmer);
    }

}

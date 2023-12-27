package com.farm_to_door.farm2door_API.Repository;

import java.util.List;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.farm_to_door.farm2door_API.DAO.HarvestDAO;
import com.farm_to_door.farm2door_API.Entity.Harvest;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class HarvestRepository implements HarvestDAO{

    private EntityManager entityManager;
    // private static final Logger logger = LoggerFactory.getLogger(HarvestRepository.class);


    public HarvestRepository(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    public void save(Harvest harvest) {
        entityManager.persist(harvest);
    }

    @Override
    public List<Harvest> getFarmerHarvests(Long farmerId) {
        String jpql = "SELECT h FROM Harvest h WHERE h.farmer.farmerId = :farmerId";
        TypedQuery<Harvest> query = entityManager.createQuery(jpql, Harvest.class);
        query.setParameter("farmerId", farmerId);
        return query.getResultList();
    }

    @Override
    public Harvest getHarvestById(Long harvest_id) {
        return entityManager.find(Harvest.class, harvest_id);
    }

    @Override
    public void updateHarvestInfo(Harvest harvest) {
        entityManager.merge(harvest);
    }    
} 

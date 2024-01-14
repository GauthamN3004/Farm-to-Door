package com.farm_to_door.farm2door_API.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.farm_to_door.farm2door_API.DAO.HarvestCategoryDAO;
import com.farm_to_door.farm2door_API.Entity.HarvestCategory;

import jakarta.persistence.EntityManager;

@Repository
public class HarvestCategoryRepository implements HarvestCategoryDAO {
    
    private EntityManager entityManager;

    public HarvestCategoryRepository(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    public List<HarvestCategory> getAllCategories() {
        String jpql = "SELECT hc FROM HarvestCategory hc";
        return entityManager.createQuery(jpql, HarvestCategory.class).getResultList();
    }

    @Override
    public HarvestCategory getCategoryById(int categoryId) {
        return entityManager.find(HarvestCategory.class, categoryId);
    }
}

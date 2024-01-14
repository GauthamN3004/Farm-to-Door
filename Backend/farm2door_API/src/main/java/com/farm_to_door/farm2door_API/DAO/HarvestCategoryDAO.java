package com.farm_to_door.farm2door_API.DAO;

import java.util.List;

import com.farm_to_door.farm2door_API.Entity.HarvestCategory;

public interface HarvestCategoryDAO {
    HarvestCategory getCategoryById(int categoryId);
    List<HarvestCategory> getAllCategories();
}
